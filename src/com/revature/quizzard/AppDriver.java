package com.revature.quizzard;

import com.revature.quizzard.util.AppContext;
import com.revature.quizzard.util.logging.Logger;

import java.util.UUID;

public class AppDriver {

    private static final Logger logger = Logger.getLogger();

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        AppContext app = new AppContext();
        app.startup();
        logger.info("Application shutdown completed");
    }

}
