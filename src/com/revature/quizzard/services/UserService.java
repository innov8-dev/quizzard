package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepo = new UserRepository();

    public AppUser registerNewUser(AppUser newUser) {

        if (newUser.getFirstName().trim().equals("") || newUser.getLastName().trim().equals("") || newUser.getEmail().trim().equals("") ||
                newUser.getUsername().trim().equals("") || newUser.getPassword().trim().equals(""))
        {
            throw new InvalidRequestException("Invalid registration values provided!");
        }

        System.out.println("[DEBUG] - User object provided: " + newUser);

        try {
            return userRepo.save(newUser);
        } catch (DataSourceException dse) {
            throw new ResourcePersistenceException(dse);
        }

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
