package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScreenTestDriver {

    public static void main(String[] args) {

        try {
            System.out.println("Starting application...\n");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            Screen currentScreen = new WelcomeScreen(consoleReader);
            currentScreen.render();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
