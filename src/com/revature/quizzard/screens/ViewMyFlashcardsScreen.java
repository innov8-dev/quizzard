package com.revature.quizzard.screens;

import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.collections.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewMyFlashcardsScreen extends Screen {

    private final UserService userService;

    public ViewMyFlashcardsScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("/my-flashcards", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {

        String ownerUsername = userService.getSessionUser().getUsername();
        BufferedReader dataReader = new BufferedReader(new FileReader("database/flashcards.txt"));
        LinkedList<Flashcard> flashcards = new LinkedList<>();

        String dataCursor;
        while ((dataCursor = dataReader.readLine()) != null) {
            String[] cardData = dataCursor.split("::");
            if (cardData[0].equals(ownerUsername)) {
                flashcards.add(new Flashcard(cardData[0], cardData[1], cardData[2]));
            }
        }

        System.out.println(ownerUsername + "'s Flashcards: \n\n");
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard card = flashcards.get(i);
            System.out.printf("Card %d:\n\tQuestion: %s\n\tAnswer: %s\n\n", (i + 1), card.getQuestionText(), card.getAnswerText());
        }

        System.out.print("Press any key and then enter to return to the dashboard.\n> ");
        consoleReader.readLine();

    }

}
