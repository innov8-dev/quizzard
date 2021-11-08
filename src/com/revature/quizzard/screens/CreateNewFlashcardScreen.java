package com.revature.quizzard.screens;

import com.revature.quizzard.Flashcard;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateNewFlashcardScreen extends Screen {

    private final String creatorUsername;

    public CreateNewFlashcardScreen(BufferedReader consoleReader, String creatorUsername) {
        super("/new-flashcard", consoleReader);
        this.creatorUsername = creatorUsername;
    }

    @Override
    public void render() throws IOException {
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
                        Flashcard newFlashcard = new Flashcard(creatorUsername, questionText, answerText);
                        FileWriter dataWriter = new FileWriter("database/flashcards.txt", true);
                        dataWriter.write(newFlashcard.toFileString());
                        dataWriter.close();
                        break;
                    default:
                        System.out.println("Confirmation check failed. Navigating back to dashboard...");
                }

                break;

            default:
                System.out.println("Confirmation check failed. Navigating back to dashboard...");
        }

    }

}
