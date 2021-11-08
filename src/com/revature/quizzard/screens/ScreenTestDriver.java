package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenTestDriver {

    public static void main(String[] args) {

        boolean appRunning = true;

        try {
            System.out.println("Starting application...\n");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            while (appRunning) {
                WelcomeScreen currentScreen = new WelcomeScreen(consoleReader);
                appRunning = currentScreen.render(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
