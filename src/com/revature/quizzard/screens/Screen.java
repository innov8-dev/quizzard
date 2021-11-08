package com.revature.quizzard.screens;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Screen {

    protected String route;
    protected BufferedReader consoleReader;

    protected Screen(String route, BufferedReader consoleReader) {
        this.route = route;
        this.consoleReader = consoleReader;
    }

    public final String getRoute() {
        return route;
    }

    public abstract void render() throws IOException;

}
