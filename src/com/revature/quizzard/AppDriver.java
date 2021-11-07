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
                     break;
                 case "2":
                     System.out.println("You selected: Register");
                     break;
                 case "3":
                     System.out.println("You selected: Exit");
                     break;
                 default:
                     System.out.println("You have made an incorrect selection.");
             }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
