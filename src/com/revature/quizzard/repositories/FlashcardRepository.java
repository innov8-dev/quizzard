package com.revature.quizzard.repositories;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.util.collections.LinkedList;
import com.revature.quizzard.util.collections.List;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FlashcardRepository implements CrudRepository<Flashcard, String> {

    private static final Logger logger = Logger.getLogger();

    private final String cardsFilePath = "database/flashcards.txt";

    public List<Flashcard> findCardsByCreatorUsername(String creatorUsername) {

        logger.info("Connecting to datasource to find details on the card with the creator: %s", creatorUsername);

        List<Flashcard> cards = new LinkedList<>();

        try (BufferedReader dataReader = new BufferedReader(new FileReader(cardsFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] cardData = dataCursor.split("::");
                System.out.println(cardData);
                if (cardData[1].equals(creatorUsername)) {
                    logger.info("Card record found");
                    cards.add(new Flashcard(cardData[0], cardData[1], cardData[2], cardData[3]));
                }
            }
        } catch (IOException e) {
            logger.error("Caught IOException with message: %s", e.getMessage());
            throw new DataSourceException(e);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return cards;

    }

    @Override
    public List<Flashcard> findAll() {
        logger.warn("Unimplemented method, FlashcardRepository#findAll, invoked");
        return null;
    }

    @Override
    public Flashcard findById(String id) {
        logger.warn("Unimplemented method, FlashcardRepository#findById, invoked");
        return null;
    }

    @Override
    public Flashcard save(Flashcard newCard) {

        logger.info("Connecting to datasource to persist new card: %s", newCard);

        try (FileWriter dataWriter = new FileWriter(cardsFilePath, true)) {
            newCard.setId(UUID.randomUUID().toString());
            String cardRecord = newCard.toFileString();
            dataWriter.write(cardRecord + "\n");
            logger.info("New card with id, %s, persisted", newCard.getId());
            return newCard;
        } catch (IOException e) {
            logger.error("Caught IOException with message: %s", e.getMessage());
            logger.warn("Unable to persist new user to data source");
            throw new DataSourceException(e);
        }
    }

    @Override
    public boolean update(Flashcard updatedObj) {
        logger.warn("Unimplemented method, FlashcardRepository#update, invoked");
        return false;
    }

    @Override
    public boolean removeById(String id) {
        logger.warn("Unimplemented method, FlashcardRepository#update, invoked");
        return false;
    }
}
