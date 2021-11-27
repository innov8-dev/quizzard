package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.Flashcard;
import com.revature.quizzard.repositories.FlashcardRepository;
import com.revature.quizzard.repositories.UserRepository;
import com.revature.quizzard.util.collections.List;
import com.revature.quizzard.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger();

    private AppUser sessionUser;
    private final UserRepository userRepo = new UserRepository();
    private final FlashcardRepository cardRepo = new FlashcardRepository();

    public AppUser registerNewUser(AppUser newUser) {

        logger.info("Registration request for user, %s, received", newUser.getUsername());

        if (newUser.getFirstName().trim().equals("") || newUser.getLastName().trim().equals("") || newUser.getEmail().trim().equals("") ||
                newUser.getUsername().trim().equals("") || newUser.getPassword().trim().equals(""))
        {
            String msg = "Invalid registration values provided";
            logger.warn(msg);
            throw new InvalidRequestException(msg);
        }

        boolean usernameTaken = !isUsernameAvailable(newUser.getUsername());
        boolean emailTaken = !isEmailAvailable(newUser.getEmail());
        if (usernameTaken || emailTaken) {
            String msg = "The values provided for the following fields are already taken: ";
            if (usernameTaken) msg = msg + "username ";
            if (emailTaken) msg = msg + "email";
            logger.warn(msg);
            throw new ResourcePersistenceException(msg);
        }

        try {
            return userRepo.save(newUser);
        } catch (DataSourceException dse) {
            logger.warn(dse.getMessage());
            throw new ResourcePersistenceException(dse);
        }

    }

    public AppUser authenticate(String username, String password) {

        logger.info("Credentials received for user with username: %s", username);

        if (username.trim().equals("") || password.trim().equals("")) {
            String msg = "Invalid credential values provided";
            logger.warn(msg);
            throw new InvalidRequestException(msg);
        }

        try {
            AppUser authenticatedUser = userRepo.findByUsernameAndPassword(username, password);
            if (authenticatedUser == null) {
                logger.info("Could not authenticate using provided credentials");
                throw new AuthenticationException();
            }
            sessionUser = authenticatedUser;
            return authenticatedUser;
        } catch (DataSourceException dse) {
            logger.warn(dse.getMessage());
            throw new AuthenticationException(dse);
        }

    }

    public boolean isUsernameAvailable(String username) {
        logger.info("Checking availability of the username: %s", username);
        return (userRepo.findByUsername(username) == null);
    }

    public boolean isEmailAvailable(String email) {
        logger.info("Checking availability of the email address: %s", email);
        return (userRepo.findByEmail(email) == null);
    }

    public AppUser getSessionUser() {
        logger.info("Fetching session user");
        return sessionUser;
    }

    public boolean isSessionActive() {
        logger.info("Checking if session is active");
        return sessionUser != null;
    }

    public void logout() {
        logger.info("Terminating current user session");
        sessionUser = null;
    }

    public List<Flashcard> getMyFlashcards() {
        logger.info("Service request to fetch current session user's flashcards received");

        if (isSessionActive()) {
            return cardRepo.findCardsByCreatorUsername(getSessionUser().getUsername());
        } else {
            throw new AuthenticationException("No active session found");
        }

    }

    public Flashcard saveNewCard(Flashcard newCard) {

        logger.info("Service request to add new flashcard to data source received");

        if (!isSessionActive()) {
            throw new AuthenticationException("No active session found");
        }

        if (newCard.getQuestionText().trim().equals("") || newCard.getAnswerText().trim().equals("")) {
            throw new InvalidRequestException("Invalid new card values provided");
        }

        newCard.setCreator(sessionUser.getUsername());

        try {
            return cardRepo.save(newCard);
        } catch (DataSourceException dse) {
            logger.warn(dse.getMessage());
            throw new ResourcePersistenceException(dse);
        }
    }
}
