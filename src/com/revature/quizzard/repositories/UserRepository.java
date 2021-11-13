package com.revature.quizzard.repositories;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.collections.List;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class UserRepository implements CrudRepository<AppUser, String> {

    private static final Logger logger = Logger.getLogger();

    private final String userFilePath = "database/users.txt";

    public AppUser findByUsername(String username) {

        logger.info("Connecting to datasource to find details on the user with the username: %s", username);

        try (BufferedReader dataReader = new BufferedReader(new FileReader(userFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] userData = dataCursor.split(":");
                if (userData[4].equals(username)) {
                    logger.info("User record found");
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4]);
                }
            }
        } catch (IOException e) {
            logger.error("Caught IOException with message: %s", e.getMessage());
            throw new DataSourceException(e);
        }

        logger.info("No user record found");
        return null;

    }

    public AppUser findByEmail(String email) {

        logger.info("Connecting to datasource to find details on the user with the email: %s", email);

        try (BufferedReader dataReader = new BufferedReader(new FileReader(userFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] userData = dataCursor.split(":");
                if (userData[3].equals(email)) {
                    logger.info("User record found");
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4]);
                }
            }
        } catch (IOException e) {
            logger.error("Caught IOException with message: %s", e.getMessage());
            throw new DataSourceException(e);
        }

        logger.info("No user record found");
        return null;

    }

    public AppUser findByUsernameAndPassword(String username, String password) {

        logger.info("Connecting to datasource to find details on the user with the username, %s, using provided password", username);

        try (BufferedReader dataReader = new BufferedReader(new FileReader(userFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] userData = dataCursor.split(":");
                if (userData[4].equals(username) && userData[5].equals(password)) {
                    logger.info("User record found");
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5]);
                }
            }
        } catch (IOException e) {
            logger.error("Caught IOException with message: %s", e.getMessage());
            throw new DataSourceException(e);
        }

        logger.info("No user record found");
        return null;

    }

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public AppUser findById(String id) {
        return null;
    }

    @Override
    public AppUser save(AppUser newUser) {

        logger.info("Connecting to datasource to persist new user with username: %s", newUser.getUsername());

        try (FileWriter dataWriter = new FileWriter(userFilePath, true)) {
            newUser.setId(UUID.randomUUID().toString());
            String userRecord = newUser.toFileString();
            dataWriter.write(userRecord + "\n");
            logger.info("New user with id, %s, persisted", newUser.getId());
            return newUser;
        } catch (IOException e) {
            logger.error("Caught IOException with message: %s", e.getMessage());
            logger.warn("Unable to persist new user to data source");
            throw new DataSourceException(e);
        }
    }

    @Override
    public boolean update(AppUser updatedObj) {
        logger.warn("Unimplemented method, UserRepository#update, invoked");
        return false;
    }

    @Override
    public boolean removeById(String id) {
        logger.warn("Unimplemented method, UserRepository#removeById, invoked");
        return false;
    }

}
