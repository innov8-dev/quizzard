package com.revature.quizzard.screens;

import static com.revature.quizzard.util.AppContext.*;

import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen {

    private static final Logger logger = Logger.getLogger();

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("/welcome", consoleReader, router);
    }

    @Override
    public void render() throws IOException {
        logger.info("Rendering WelcomeScreen");
        String welcomeMenu = "Welcome to Quizzard!\n" +
                "Please make a selection from the options below:\n" +
                "1) Login\n" +
                "2) Register\n" +
                "3) Exit\n" +
                "> ";

        System.out.print(welcomeMenu);

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/login");
                break;
            case "2":
                router.navigate("/register");
                break;
            case "3":
                System.out.println("Exiting application...");
                shutdown();
                break;
            default:
                logger.info("User made invalid selection on WelcomeScreen");
                System.out.println("You have made an incorrect selection.");
        }
    }
}
