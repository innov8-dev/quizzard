package com.revature.quizzard.util;

import com.revature.quizzard.screens.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppContext {

    private static boolean appRunning;
    private final ScreenRouter router;

    public AppContext() {
        appRunning = true;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(console, router));
        router.addScreen(new RegisterScreen(console, router));
        router.addScreen(new LoginScreen(console, router));
        router.addScreen(new DashboardScreen(console, router, null)); // TODO refactor to not require third param
        router.addScreen(new ViewMyFlashcardsScreen(console, router, null)); // TODO refactor to not require third param
        router.addScreen(new CreateNewFlashcardScreen(console, router, null)); // TODO refactor to not require third param
    }

    public void startup() {
        try {
            while (appRunning) {
                router.navigate("/welcome");
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("An unhandled exception occurred and the application must terminate now.");
        } finally {
            appRunning = false;
        }
    }

    public static void shutdown() {
        appRunning = false;
    }

}
