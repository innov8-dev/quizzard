package com.revature.quizzard.util.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static Logger logger;
    private final boolean printToConsole;

    private Logger(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public static Logger getLogger() {
        return getLogger(false);
    }

    public static Logger getLogger(boolean printToConsole) {
        if (logger == null) {
            logger = new Logger(printToConsole);
        }

        return logger;
    }

    public void info(String message, Object... args) {
        try (Writer writer = new FileWriter("resources/logs/app.log", true)) {
            String formattedMessage = formatMessage("INFO", String.format(message, args));
            writer.write(formattedMessage + "\n");

            if (printToConsole) {
                printMessageToConsole("INFO", formattedMessage);
            }
        } catch (IOException e) {
            printMessageToConsole("ERROR", "Could not write message to file");
        }
    }

    public void warn(String message, Object... args) {
        try (Writer writer = new FileWriter("resources/logs/app.log", true)) {
            String formattedMessage = formatMessage("WARN", String.format(message, args));
            writer.write(formattedMessage + "\n");

            if (printToConsole) {
                printMessageToConsole("WARN", formattedMessage);
            }
        } catch (IOException e) {
            printMessageToConsole("ERROR", "Could not write message to file");
        }
    }

    public void error(String message, Object... args) {
        try (Writer writer = new FileWriter("resources/logs/app.log", true)) {
            String formattedMessage = formatMessage("ERROR", String.format(message, args));
            writer.write(formattedMessage + "\n");

            if (printToConsole) {
                printMessageToConsole("ERROR", formattedMessage);
            }
        } catch (IOException e) {
            printMessageToConsole("ERROR", "Could not write message to file");
        }
    }

    public void fatal(String message, Object... args) {
        try (Writer writer = new FileWriter("resources/logs/app.log", true)) {
            String formattedMessage = formatMessage("FATAL", String.format(message, args));
            writer.write(formattedMessage + "\n");

            if (printToConsole) {
                printMessageToConsole("FATAL", formattedMessage);
            }
        } catch (IOException e) {
            printMessageToConsole("ERROR", "Could not write message to file");
        }
    }

    private String formatMessage(String level, String message) {
        return String.format("[%s] %s at %s", level, message, System.currentTimeMillis());
    }

    private void printMessageToConsole(String level, String message) {
        switch (level) {
            case "INFO":
                System.out.println(message);
                break;
            case "WARN":
                System.out.println(ANSI_YELLOW + message + ANSI_RESET);
                break;
            case "ERROR":
            case "FATAL":
                System.out.println(ANSI_RED + message + ANSI_RESET);
                break;
        }
    }

}
