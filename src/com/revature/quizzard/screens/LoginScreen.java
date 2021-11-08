package com.revature.quizzard.screens;

import com.revature.quizzard.AppUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen extends Screen {

    public LoginScreen() {
        super("/login");
    }

    @Override
    public void render() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nLog into your account using your credentials");
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();

        if (username.trim().equals("") || password.trim().equals("")) {
            System.err.println("You have provided invalid values. Navigating back to Welcome Screen...");
            // TODO navigate back to WelcomeScreen
            return;
        }

        System.out.println("[DEBUG] - You entered: \n\tUsername - " + username + "\n\tPassword - " + password);

        BufferedReader dataReader = new BufferedReader(new FileReader("database/users.txt"));

        String dataCursor;
        while ((dataCursor = dataReader.readLine()) != null) {
            String[] userData = dataCursor.split(":");
            if (userData[3].equals(username) && userData[4].equals(password)) {
                System.out.println("[DEBUG] - User found with matching credentials: " + dataCursor);
                System.out.println("Credentials verified. Navigating to dashboard...");
                AppUser authenticatedUser = new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4]);
                new DashboardScreen(authenticatedUser).render();
                return;
            }
        }

        System.err.println("No user found with matching credentials.");

    }

}
