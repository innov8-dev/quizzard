package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class DashboardScreen extends Screen {

    private final AppUser authenticatedUser;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, AppUser authenticatedUser) {
        super("/dashboard", consoleReader, router);
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
                    router.navigate("/new-flashcard");
                    break;
                case "2":
                    System.out.println("[DEBUG] - View Flashcards selected. Not implemented.");
                    router.navigate("/my-flashcards");
                    break;
                case "3":
                    System.out.println("Logging out and navigating back to Welcome Screen.");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("You have made an incorrect selection.");
            }
        }

    }

}
