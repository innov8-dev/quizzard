package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(BufferedReader consoleReader) {
        super("/welcome", consoleReader);
    }

    @Override
    public void render() throws IOException {

        String welcomeMenu = "Welcome to Quizzard!\n" +
                "Please make a selection from the options below:\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit\n" +
                "> ";

        System.out.print(welcomeMenu);

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                new LoginScreen(consoleReader).render();
                break;
            case "2":
                new RegisterScreen(consoleReader).render();
                break;
            case "3":
                System.out.println("[DEBUG] - Exit selected. Not implemented.");
                break;
            default:
                System.out.println("You have made an incorrect selection.");
        }

    }
}
