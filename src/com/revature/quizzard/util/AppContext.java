package com.revature.quizzard.util;

import com.revature.quizzard.screens.*;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppContext {

    private static final Logger logger = Logger.getLogger(true);
    private static boolean appRunning;
    private final ScreenRouter router;

    public AppContext() {

        logger.info("Application initialization started at %s", System.currentTimeMillis());

        appRunning = true;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        router = new ScreenRouter();

        UserService userService = new UserService();

        logger.info("ScreenRouter population started at %s", System.currentTimeMillis());
        router.addScreen(new WelcomeScreen(console, router));
        router.addScreen(new RegisterScreen(console, router, userService));
        router.addScreen(new LoginScreen(console, router, userService));
        router.addScreen(new DashboardScreen(console, router, userService));
        router.addScreen(new ViewMyFlashcardsScreen(console, router, userService));
        router.addScreen(new CreateNewFlashcardScreen(console, router, userService));
        logger.info("ScreenRouter population completed at %s", System.currentTimeMillis());

        logger.info("Application initialization completed at %s", System.currentTimeMillis());
        logger.warn("Test warning");
        logger.error("Test error");
        logger.fatal("Test fatal");

    }

    public void startup() {
        try {
            while (appRunning) {
                router.navigate("/welcome");
            }
        } catch (Throwable t) {
            logger.fatal("An unhandled exception of type %s occurred with the message: %s", t.getClass().getSimpleName(), t.getMessage());
            System.out.println("An unhandled exception occurred and the application must terminate now.");
        } finally {
            shutdown();
        }
    }

    public static void shutdown() {
        logger.info("Application shutdown request received at %s", System.currentTimeMillis());
        appRunning = false;
    }

}
