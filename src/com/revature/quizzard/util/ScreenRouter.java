package com.revature.quizzard.util;

import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.util.collections.LinkedList;
import com.revature.quizzard.util.collections.List;

import java.io.IOException;

public class ScreenRouter {

    private final List<Screen> appScreens;

    public ScreenRouter() {
        appScreens = new LinkedList<>();
    }

    public void addScreen(Screen screen) {
        appScreens.add(screen);
    }

    public void navigate(String route) throws IOException {
        for (int i = 0; i < appScreens.size(); i++) {
            Screen thisScreen = appScreens.get(i);
            if (thisScreen.getRoute().equals(route)) {
                thisScreen.render();
            }
        }
    }

}
