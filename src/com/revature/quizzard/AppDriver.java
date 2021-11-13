package com.revature.quizzard;

import com.revature.quizzard.util.AppContext;
import com.revature.quizzard.util.logging.Logger;

public class AppDriver {

    private static final Logger logger = Logger.getLogger(true);

    public static void main(String[] args) {
        AppContext app = new AppContext();
        app.startup();
        logger.info("Application shutdown completed");
    }

}
