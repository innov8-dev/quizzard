package com.revature.quizzard.util;

public class AppContext {

    private static boolean appRunning;

    public AppContext() {
        appRunning = true;
    }

    public static void startup() {

    }

    public static void shutdown() {
        appRunning = false;
    }

}
