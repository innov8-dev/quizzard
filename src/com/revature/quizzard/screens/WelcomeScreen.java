package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WelcomeScreen extends Screen {

    public WelcomeScreen() {
        super("/welcome");
    }

    @Override
    public void render() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

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
                new LoginScreen().render();
                break;
            case "2":
                new RegisterScreen().render();
                break;
            case "3":
                System.out.println("[DEBUG] - Exit selected. Not implemented.");
                break;
            default:
                System.out.println("You have made an incorrect selection.");
        }

    }
}
