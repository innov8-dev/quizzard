package com.revature.quizzard.screens;

import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateNewFlashcardScreen extends Screen {

    private final UserService userService;

    public CreateNewFlashcardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("/new-flashcard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {

        logger.info("Rendering CreateNewFlashcardScreen");

        System.out.print("Please enter the question text for the flashcard:\n> ");
        String questionText = consoleReader.readLine();

        System.out.println("You provided: \n\n" + questionText + "\n\n" + "Is this correct? (y/n)");
        String confirmation = consoleReader.readLine();

        switch (confirmation) {
            case "y":
            case "Y":
            case "yes":
            case "YES":
            case "Yes":
                logger.info("User confirmed the correctness of the provided question text");
                System.out.print("Please enter the answer text for the flashcard:\n> ");
                String answerText = consoleReader.readLine();

                System.out.println("You provided: \n\n" + answerText + "\n\n" + "Is this correct? (y/n)");
                confirmation = consoleReader.readLine();

                switch (confirmation) {
                    case "y":
                    case "Y":
                    case "yes":
                    case "YES":
                    case "Yes":
                        logger.info("User confirmed the correctness of the provided answer text");
                        userService.saveNewCard(new Flashcard(questionText, answerText));
                        break;
                    default:
                        logger.info("User did not confirm the correctness of the provided answer text");
                        System.out.println("Confirmation check failed. Navigating back to dashboard...");
                }

                break;

            default:
                logger.info("User did not confirm the correctness of the provided question text");
                System.out.println("Confirmation check failed. Navigating back to dashboard...");
        }

    }

}
