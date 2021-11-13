package com.revature.quizzard.util.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

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

    public void log(String message, Object... args) {

        try (Writer logWriter = new FileWriter("resources/logs/app.log", true)) {

            String formattedMsg = String.format(message, args);
            logWriter.write(formattedMsg + "\n");

            if (printToConsole) {
                System.out.println(ANSI_YELLOW + formattedMsg + ANSI_RESET);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
