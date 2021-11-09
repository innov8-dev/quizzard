package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repositories.UserRepository;
import com.revature.quizzard.screens.DashboardScreen;

import java.io.BufferedReader;
import java.io.FileReader;


public class UserService {

    private final UserRepository userRepo = new UserRepository();

    public AppUser registerNewUser(AppUser newUser) {

        if (newUser.getFirstName().trim().equals("") || newUser.getLastName().trim().equals("") || newUser.getEmail().trim().equals("") ||
                newUser.getUsername().trim().equals("") || newUser.getPassword().trim().equals(""))
        {
            throw new InvalidRequestException("Invalid registration values provided!");
        }

        System.out.println("[DEBUG] - User object provided: " + newUser);

        return userRepo.save(newUser);

    }

    public AppUser authenticate(String username, String password) {

        if (username.trim().equals("") || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        System.out.println("[DEBUG] - Credentials provided: {username=" + username + ", password=" + password + "}");

        try {
            return userRepo.findByUsernameAndPassword(username, password);
        } catch (ResourceNotFoundException rnfe) {
            throw new AuthenticationException();
        } catch (DataSourceException dse) {
            throw new AuthenticationException(dse);
        }


    }

}
