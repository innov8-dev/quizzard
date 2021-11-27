package com.revature.quizzard.screens;

import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.services.FlashcardService;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.collections.List;

import java.io.BufferedReader;
import java.io.IOException;

public class ViewMyFlashcardsScreen extends Screen {

    private final UserService userService;
    private final FlashcardService cardService;

    public ViewMyFlashcardsScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, FlashcardService cardService) {
        super("/my-flashcards", consoleReader, router);
        this.userService = userService;
        this.cardService = cardService;
    }

    @Override
    public void render() throws IOException {

        logger.info("Rendering ViewMyFlashcardsScreen");

        String ownerUsername = userService.getSessionUser().getUsername();

        List<Flashcard> flashcards = cardService.getMyFlashcards();

        System.out.println(ownerUsername + "'s Flashcards: \n\n");
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard card = flashcards.get(i);
            System.out.printf("Card %d:\n\tQuestion: %s\n\tAnswer: %s\n\n", (i + 1), card.getQuestionText(), card.getAnswerText());
        }

        System.out.print("Press any key and then enter to return to the dashboard.\n> ");
        consoleReader.readLine();

    }

}
