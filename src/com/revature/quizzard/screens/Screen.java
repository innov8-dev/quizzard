package com.revature.quizzard.screens;

import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Screen {

    protected String route;
    protected BufferedReader consoleReader;
    protected ScreenRouter router;

    protected Screen(String route, BufferedReader consoleReader, ScreenRouter router) {
        this.route = route;
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public final String getRoute() {
        return route;
    }

    public abstract void render() throws IOException;

}
