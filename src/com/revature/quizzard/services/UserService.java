package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserService {

    public AppUser registerNewUser(AppUser newUser) {

        if (newUser.getFirstName().trim().equals("") || newUser.getLastName().trim().equals("") || newUser.getEmail().trim().equals("") ||
                newUser.getUsername().trim().equals("") || newUser.getPassword().trim().equals(""))
        {
            throw new InvalidRequestException("Invalid registration values provided!");
        }

        System.out.println("[DEBUG] - User object provided: " + newUser);

        String dataString = newUser.toFileString();

        try(FileWriter dataWriter = new FileWriter("database/users.txt", true)) {
            dataWriter.write(dataString);
            return newUser;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourcePersistenceException();
        }

    }

    public AppUser authenticate(String username, String password) {
        return null;
    }

}
