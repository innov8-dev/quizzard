package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {

    private final UserService userService = new UserService();

    public LoginScreen(BufferedReader consoleReader) {
        super("/login", consoleReader);
    }

    @Override
    public void render() throws IOException {

        System.out.println("\nLog into your account using your credentials");
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();

        try {
            AppUser authenticatedUser = userService.authenticate(username, password);
            new DashboardScreen(consoleReader, authenticatedUser).render();
        } catch (AuthenticationException ae) {
            System.out.println(ae.getMessage());
        }

        System.out.println("Navigating back to Welcome Screen...");

    }

}
