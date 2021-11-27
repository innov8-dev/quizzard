package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.repositories.FlashcardRepository;
import com.revature.quizzard.util.collections.List;
import com.revature.quizzard.util.logging.Logger;

public class FlashcardService {

    private final static Logger logger = Logger.getLogger();

    public final FlashcardRepository cardRepo = new FlashcardRepository();
    public final UserService userService;

    public FlashcardService(UserService userService) {
        this.userService = userService;
    }

    public List<Flashcard> getMyFlashcards() {
        logger.info("Service request to fetch current session user's flashcards received");

        if (userService.isSessionActive()) {
            return cardRepo.findCardsByCreatorUsername(userService.getSessionUser().getUsername());
        } else {
            throw new AuthenticationException("No active session found");
        }

    }

    public Flashcard saveNewCard(Flashcard newCard) {

        logger.info("Service request to add new flashcard to data source received");

        if (!userService.isSessionActive()) {
            throw new AuthenticationException("No active session found");
        }

        if (newCard.getQuestionText().trim().equals("") || newCard.getAnswerText().trim().equals("")) {
            throw new InvalidRequestException("Invalid new card values provided");
        }

        newCard.setCreator(userService.getSessionUser().getUsername());

        try {
            return cardRepo.save(newCard);
        } catch (DataSourceException dse) {
            logger.warn(dse.getMessage());
            throw new ResourcePersistenceException(dse);
        }
    }
}
