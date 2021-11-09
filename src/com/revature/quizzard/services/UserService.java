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

        boolean usernameTaken = !isUsernameAvailable(newUser.getUsername());
        boolean emailTaken = !isEmailAvailable(newUser.getEmail());
        if (usernameTaken || emailTaken) {
            String msg = "The values provided for the following fields are already taken:";
            if (usernameTaken) msg = msg + "\n\t- username";
            if (emailTaken) msg = msg + "\n\t- email";
            throw new ResourcePersistenceException(msg);
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
            AppUser authenticatedUser = userRepo.findByUsernameAndPassword(username, password);
            if (authenticatedUser == null) {
                throw new AuthenticationException();
            }
            return authenticatedUser;
        } catch (DataSourceException dse) {
            throw new AuthenticationException(dse);
        }

    }

    public boolean isUsernameAvailable(String username) {
        return (userRepo.findByUsername(username) == null);
    }

    public boolean isEmailAvailable(String email) {
        return (userRepo.findByEmail(email) == null);
    }

}
