package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.*;

public class RegisterScreen extends Screen {

    private final UserService userService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("/register", consoleReader, router);
        this.userService = userService;
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
            logger.info("Persisted user: " + registeredUser);
        } catch (InvalidRequestException ire) {
            System.out.println("You provided invalid registration values. Please try again.");
        } catch (ResourcePersistenceException rpe) {
            System.out.println(rpe.getMessage());
        }

        System.out.println("Navigating back to Welcome Screen...");

    }

}
