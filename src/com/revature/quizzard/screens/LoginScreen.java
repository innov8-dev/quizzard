package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {

    private final UserService userService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("/login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws IOException {

        logger.info("Rendering LoginScreen");

        System.out.println("\nLog into your account using your credentials");
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();

        try {
            AppUser authenticatedUser = userService.authenticate(username, password);
            router.navigate("/dashboard");
        } catch (AuthenticationException ae) {
            logger.warn(ae.getMessage());
        }

        System.out.println("Navigating back to Welcome Screen...");

    }

}
