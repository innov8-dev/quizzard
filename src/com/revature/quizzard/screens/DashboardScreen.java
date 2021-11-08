package com.revature.quizzard.screens;

import com.revature.quizzard.AppUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DashboardScreen extends Screen {

    private final AppUser authenticatedUser;

    public DashboardScreen(AppUser authenticatedUser) {
        super("/dashboard");
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public void render() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.print("Welcome, " + authenticatedUser.getFirstName() + "!\n" +
                    "Please make a selection:\n" +
                    "1) Create a flashcard\n" +
                    "2) View my flashcards\n" +
                    "3) Logout\n" +
                    "> ");

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("[DEBUG] - Create Flashcard selected. Not implemented.");
                    // TODO navigate to CreateFlashcardScreen
                    break;
                case "2":
                    System.out.println("[DEBUG] - View Flashcards selected. Not implemented.");
                    // TODO navigate to ViewFlashcardScreen
                    break;
                case "3":
                    System.out.println("Logging out and navigating back to Welcome Screen.");
                    loggedIn = false;
                    // TODO navigate to WelcomeScreen
                    break;
                default:
                    System.out.println("You have made an incorrect selection.");
            }
        }

    }

}