package com.revature.quizzard.screens;

import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class DashboardScreen extends Screen {

    private final UserService userService;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {

        while (userService.isSessionActive()) {
            System.out.print("Welcome, " + userService.getSessionUser().getFirstName() + "!\n" +
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
                    userService.logout();
                    break;
                default:
                    System.out.println("You have made an incorrect selection.");
            }
        }

    }

}
