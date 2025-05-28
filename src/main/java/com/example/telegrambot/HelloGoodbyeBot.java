package com.example.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Un bot de Telegram simple que responde "adios" cuando recibe "hola".
 */
public class HelloGoodbyeBot extends TelegramLongPollingBot {

    private String botToken;
    private final String BOT_USERNAME = "MiPrimerBot"; // Puedes cambiar esto si quieres, pero no es crítico para la funcionalidad.

    public HelloGoodbyeBot() {
        super();
        loadToken();
    }
    
    /**
     * Constructor que permite inyectar el token, útil para pruebas o configuraciones alternativas.
     * @param botToken El token del bot de Telegram.
     */
    public HelloGoodbyeBot(String botToken) {
        super(botToken); // Llama al constructor de la superclase que espera el token
        this.botToken = botToken;
    }

    private void loadToken() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("bot.properties")) {
            if (input == null) {
                System.err.println("No se pudo encontrar el archivo bot.properties");
                this.botToken = "YOUR_BOT_TOKEN_HERE"; // Fallback por si no se carga el archivo
                return;
            }
            prop.load(input);
            this.botToken = prop.getProperty("TELEGRAM_TOKEN");
            if (this.botToken == null || this.botToken.equals("YOUR_BOT_TOKEN_HERE") || this.botToken.trim().isEmpty()) {
                System.err.println("TELEGRAM_TOKEN no encontrado o es el placeholder en bot.properties. Por favor, configura tu token.");
                // Considera lanzar una excepción aquí o manejarlo de forma más robusta si es necesario.
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            this.botToken = "YOUR_BOT_TOKEN_HERE"; // Fallback en caso de error de IO
        }
    }

    /**
     * Este método se invoca cuando el bot recibe una actualización (un mensaje nuevo, etc.).
     * @param update El objeto Update que contiene los detalles del mensaje.
     */
    @Override
    public void onUpdateReceived(Update update) {
        // Verifica si la actualización tiene un mensaje y si el mensaje tiene texto
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().trim().toLowerCase();
            long chatId = update.getMessage().getChatId();

            if ("hola".equals(messageText)) {
                SendMessage message = new SendMessage(); // Crear objeto SendMessage
                message.setChatId(String.valueOf(chatId));
                message.setText("adios");
                try {
                    execute(message); // Enviar el mensaje
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Devuelve el nombre de usuario del bot.
     * @return El nombre de usuario del bot.
     */
    @Override
    public String getBotUsername() {
        // Puedes retornar un nombre fijo o cargarlo de properties si lo deseas
        return BOT_USERNAME;
    }

    /**
     * Devuelve el token del bot para conectarse a la API de Telegram.
     * Este método es consultado por la librería TelegramBots al iniciar.
     * @return El token del bot.
     */
    @Override
    public String getBotToken() {
        // El token se carga en el constructor desde bot.properties
        // La librería llama a este método para obtener el token.
        // Si se usó el constructor HelloGoodbyeBot(String token), se usará ese token.
        // Si se usó el constructor por defecto, se usará el token cargado desde loadToken().
        // La superclase TelegramLongPollingBot tiene su propio manejo interno del token si se le pasa en su constructor.
        // Si se llama al constructor super() sin argumentos, entonces este método getBotToken() debe devolver el token.
        return this.botToken;
    }
}
