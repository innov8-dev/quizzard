package com.revature.quizzard;

import java.io.*;

public class AppDriver {

    public static void main(String[] args) {

        String userSelection;
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            boolean appRunning = true;

            while (appRunning) {

                String welcomeMenu = "Welcome to Quizzard!\n" +
                        "Please make a selection from the options below:\n" +
                        "1) Login\n" +
                        "2) Register\n" +
                        "3) Exit\n" +
                        "> ";

                System.out.print(welcomeMenu);

                userSelection = consoleReader.readLine();

                switch (userSelection) {
                    case "1":
                        login(consoleReader);
                        break;
                    case "2":
                        register(consoleReader);
                        break;
                    case "3":
                        System.out.println("You selected: Exit");
                        appRunning = false;
                        break;
                    default:
                        System.out.println("You have made an incorrect selection.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void login(BufferedReader consoleReader) throws IOException {
        System.out.println("You selected: Login.");
        System.out.println("Log into your account using your credentials");
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();
        System.out.println("You entered: \nUsername - " + username + "\nPassword - " + password);

        BufferedReader dataReader = new BufferedReader(new FileReader("database/users.txt"));
        String dataCursor;
        while ((dataCursor = dataReader.readLine()) != null) {
            String[] userData = dataCursor.split(":");
            if (userData[3].equals(username) && userData[4].equals(password)) {
                System.out.println("User found with matching credentials: " + dataCursor);
                return;
            }
        }
        System.out.println("No user found with matching credentials.");

    }

    public static void register(BufferedReader consoleReader) throws IOException {
        System.out.println("You selected: Register");
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
