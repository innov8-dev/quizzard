package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.InvalidRequestException;
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

        return userRepo.save(newUser);

    }

    public AppUser authenticate(String username, String password) {
        return null;
    }

}
