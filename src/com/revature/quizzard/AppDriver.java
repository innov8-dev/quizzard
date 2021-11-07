package com.revature.quizzard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppDriver {

    public static void main(String[] args) {

        String welcomeMenu = "Welcome to Quizzard!\n" +
                             "Please make a selection from the options below:\n" +
                             "1) Login\n" +
                             "2) Register\n" +
                             "3) Exit\n" +
                             "> ";

        System.out.print(welcomeMenu);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String userSelection;
        try {
             userSelection = consoleReader.readLine();

             switch (userSelection) {
                 case "1":
                     System.out.println("You selected: Login.");
                     System.out.println("Log into your account using your credentials");
                     System.out.print("Username > ");
                     String username = consoleReader.readLine();
                     System.out.print("Password > ");
                     String password = consoleReader.readLine();
                     System.out.println("You entered: \nUsername - " + username + "\nPassword - " + password);
                     break;
                 case "2":
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

                     break;
                 case "3":
                     System.out.println("You selected: Exit");
                     return;
                 default:
                     System.out.println("You have made an incorrect selection.");
             }
        } catch (IOException e) {
            e.printStackTrace();
        }

        main(args);

    }

}
