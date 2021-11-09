package com.revature.quizzard.screens;

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

        AppUser registeredUser = userService.registerNewUser(new AppUser(firstName, lastName, email, username, password));
        if (registeredUser != null) {
            System.out.println("Registration successful! Navigating back to Welcome Screen...");
        } else {
            System.out.println("Registration unsuccessful! Please try again later.");
        }

    }

}
