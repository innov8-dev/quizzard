package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.*;

public class RegisterScreen extends Screen {

    private final UserService userService = new UserService();

    public RegisterScreen(BufferedReader consoleReader) {
        super("/register", consoleReader);
    }

    @Override
    public void render() throws IOException {

        System.out.println("Please provide some information to register an account");
        System.out.print("First name > ");
        String firstName = consoleReader.readLine();
        System.out.print("Last name > ");
        String lastName = consoleReader.readLine();
        System.out.print("Email > ");
        String email = consoleReader.readLine();
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();

        try {
            AppUser registeredUser = userService.registerNewUser(new AppUser(firstName, lastName, email, username, password));
            System.out.println("[DEBUG] - Persisted user: " + registeredUser);
        } catch (InvalidRequestException ire) {
            System.out.println("You provided invalid registration values. Please try again.");
        } catch (ResourcePersistenceException rpe) {
            System.out.println(rpe.getMessage());
        }

        System.out.println("Navigating back to Welcome Screen...");

    }

}
