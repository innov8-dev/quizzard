package com.revature.quizzard.screens;

import java.io.IOException;

public class ScreenTestDriver {

    public static void main(String[] args) {

        try {

            System.out.println("Starting application...\n");
            Screen currentScreen = new WelcomeScreen();
            currentScreen.render();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
