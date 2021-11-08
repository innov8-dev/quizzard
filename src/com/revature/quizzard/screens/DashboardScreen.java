package com.revature.quizzard.screens;

import com.revature.quizzard.AppUser;

import java.io.BufferedReader;
import java.io.IOException;

public class DashboardScreen extends Screen {

    private final AppUser authenticatedUser;

    public DashboardScreen(BufferedReader consoleReader, AppUser authenticatedUser) {
        super("/dashboard", consoleReader);
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public void render() throws IOException {

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
                    new CreateNewFlashcardScreen(consoleReader, authenticatedUser.getUsername()).render();
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
