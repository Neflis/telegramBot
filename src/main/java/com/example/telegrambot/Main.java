package com.example.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Clase principal para iniciar el bot de Telegram.
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     * Inicializa la API de bots de Telegram y registra el {@link HelloGoodbyeBot}.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            HelloGoodbyeBot bot = new HelloGoodbyeBot();
            botsApi.registerBot(bot);
            System.out.println(bot.getBotUsername() + " está iniciado y esperando mensajes...");
            System.out.println("Prueba enviando 'hola' al bot en Telegram.");
            System.out.println("Asegúrate de que el TELEGRAM_TOKEN en bot.properties es correcto.");

        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar o registrar el bot: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
}
