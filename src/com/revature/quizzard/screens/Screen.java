package com.revature.quizzard.screens;

import java.io.IOException;

public abstract class Screen {

    protected String route;

    protected Screen(String route) {
        this.route = route;
    }

    public final String getRoute() {
        return route;
    }

    public abstract void render() throws IOException;

}
