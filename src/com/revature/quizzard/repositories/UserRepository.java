package com.revature.quizzard.repositories;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class UserRepository implements CrudRepository<AppUser, String> {

    private final String userFilePath = "database/users.txt";

    public AppUser findByUsername(String username) {

        try (BufferedReader dataReader = new BufferedReader(new FileReader(userFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] userData = dataCursor.split(":");
                if (userData[4].equals(username)) {
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4]);
                }
            }
        } catch (IOException e) {
            throw new DataSourceException(e);
        }

        return null;

    }

    public AppUser findByEmail(String email) {

        try (BufferedReader dataReader = new BufferedReader(new FileReader(userFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] userData = dataCursor.split(":");
                if (userData[3].equals(email)) {
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4]);
                }
            }
        } catch (IOException e) {
            throw new DataSourceException(e);
        }

        return null;

    }

    public AppUser findByUsernameAndPassword(String username, String password) {

        try (BufferedReader dataReader = new BufferedReader(new FileReader(userFilePath))) {
            String dataCursor;
            while ((dataCursor = dataReader.readLine()) != null) {
                String[] userData = dataCursor.split(":");
                if (userData[4].equals(username) && userData[5].equals(password)) {
                    return new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5]);
                }
            }
        } catch (IOException e) {
            throw new DataSourceException(e);
        }

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
        try (FileWriter dataWriter = new FileWriter(userFilePath, true)) {
            newUser.setId(UUID.randomUUID().toString());
            String userRecord = newUser.toFileString();
            dataWriter.write(userRecord + "\n");
            return newUser;
        } catch (IOException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public boolean update(AppUser updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

}
