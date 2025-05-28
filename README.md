# HelloGoodbyeBot - Bot de Telegram Simple

Este es un bot de Telegram simple desarrollado en Java 11 que responde "adios" cuando un usuario envía el mensaje "hola".

## Requisitos Previos

- Java 11 JDK o superior.
- Apache Maven.
- Una cuenta de Telegram.

## Configuración

1.  **Obtener un Token de Bot de Telegram:**
    *   Abre Telegram y busca `@BotFather`.
    *   Envía el comando `/newbot`.
    *   Sigue las instrucciones para elegir un nombre y un nombre de usuario para tu bot.
    *   BotFather te proporcionará un **token de acceso HTTP API**. Este token es crucial. Guárdalo de forma segura.

2.  **Configurar el Token en la Aplicación:**
    *   Dentro del proyecto, navega a `src/main/resources/`.
    *   Encontrarás un archivo llamado `bot.properties`.
    *   Abre `bot.properties` y reemplaza `YOUR_BOT_TOKEN_HERE` con el token que obtuviste de BotFather. Ejemplo:
        ```properties
        TELEGRAM_TOKEN=1234567890:ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmno
        ```

## Compilación y Ejecución

1.  **Compilar el Proyecto:**
    *   Abre una terminal o línea de comandos en la raíz del proyecto (donde se encuentra el archivo `pom.xml`).
    *   Ejecuta el siguiente comando Maven para limpiar el proyecto, compilar el código y empaquetarlo en un archivo JAR ejecutable:
        ```bash
        mvn clean package
        ```
    *   Esto generará un archivo JAR en el directorio `target/`, por ejemplo, `telegram-bot-1.0-SNAPSHOT-jar-with-dependencies.jar`.

2.  **Ejecutar la Aplicación:**
    *   Una vez compilado, ejecuta el bot usando el siguiente comando (asegúrate de reemplazar el nombre del archivo JAR si es diferente):
        ```bash
        java -jar target/telegram-bot-1.0-SNAPSHOT-jar-with-dependencies.jar
        ```
    *   Si todo está configurado correctamente, verás un mensaje en la consola indicando que el bot se ha iniciado y está esperando mensajes.

3.  **Probar el Bot:**
    *   Abre Telegram y busca el nombre de usuario de tu bot.
    *   Envía el mensaje "hola" (puede ser con mayúsculas o minúsculas, con o sin espacios al inicio/final).
    *   El bot debería responder "adios".
    *   Cualquier otro mensaje será ignorado.

## Estructura del Paquete

El código fuente principal se encuentra en el paquete `com.example.telegrambot`.

-   `HelloGoodbyeBot.java`: Contiene la lógica principal del bot, incluyendo la conexión a Telegram y el manejo de mensajes.
-   `Main.java`: Clase de entrada para ejecutar la aplicación.
-   `bot.properties`: Archivo de configuración para el token del bot.
