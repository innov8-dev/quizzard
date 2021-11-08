package com.revature.quizzard.screens;

import java.io.*;

public class RegisterScreen extends Screen {

    public RegisterScreen(BufferedReader consoleReader) {
        super("/register", consoleReader);
    }

    @Override
    public void render() throws IOException {

        System.out.println("Please provide some information to register an account");
        System.out.print("First name > ");
        String firstName = consoleReader.readLine();
        System.out.print("Last name > ");
        String lastName = consoleReader.readLine();
        System.out.print("Email > ");
        String email = consoleReader.readLine();
        System.out.print("Username > ");
        String registerUsername = consoleReader.readLine();
        System.out.print("Password > ");
        String registerPassword = consoleReader.readLine();

        if (firstName.trim().equals("") || lastName.trim().equals("") || email.trim().equals("") ||
                registerUsername.trim().equals("") || registerPassword.trim().equals(""))
        {
            System.err.println("You have provided invalid values. Navigating back to Welcome Screen...");
            return;
        }

        System.out.printf("You entered: \n" +
                "First name - %s\n" +
                "Last name - %s\n" +
                "Email - %s\n" +
                "Username - %s\n" +
                "Password - %s\n", firstName, lastName, email, registerUsername, registerPassword);

        String dataString = firstName + ":" + lastName + ":" + email + ":" + registerUsername + ":" + registerPassword + "\n";

        File usersDataFile = new File("database/users.txt");
        FileWriter dataWriter = new FileWriter(usersDataFile, true);
        dataWriter.write(dataString);
        dataWriter.close();

    }

}
