package com.revature.quizzard;

import com.revature.quizzard.util.LinkedList;

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

        if (username.trim().equals("") || password.trim().equals("")) {
            System.err.println("You have provided invalid values. Navigating back to Welcome Screen...");
            return;
        }

        System.out.println("You entered: \nUsername - " + username + "\nPassword - " + password);

        BufferedReader dataReader = new BufferedReader(new FileReader("database/users.txt"));
        String dataCursor;
        while ((dataCursor = dataReader.readLine()) != null) {
            String[] userData = dataCursor.split(":");
            if (userData[3].equals(username) && userData[4].equals(password)) {
                System.out.println("User found with matching credentials: " + dataCursor);
                AppUser authenticatedUser = new AppUser(userData[0], userData[1], userData[2], userData[3], userData[4]);
                dashboard(consoleReader, authenticatedUser);
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

    public static void dashboard(BufferedReader consoleReader, AppUser authenticatedUser) throws IOException {

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.print("Welcome, " + authenticatedUser.getFirstName() + "!\n" +
                    "Please make a selection:\n" +
                    "1) Create a flashcard\n" +
                    "2) View my flashcards\n" +
                    "3) Logout\n" +
                    "> ");

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    createFlashcard(consoleReader, authenticatedUser.getUsername());
                    break;
                case "2":
                    viewFlashcards(consoleReader, authenticatedUser.getUsername());
                    break;
                case "3":
                    System.out.println("You selected: Logout");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("You have made an incorrect selection.");
            }
        }

    }

    public static void createFlashcard(BufferedReader consoleReader, String creatorUsername) throws IOException {

        System.out.println("You selected: Create a flashcard");
        System.out.print("Please enter the question text for the flashcard:\n> ");
        String questionText = consoleReader.readLine();

        System.out.println("You provided: \n\n" + questionText + "\n\n" + "Is this correct? (y/n)");
        String confirmation = consoleReader.readLine();

        switch (confirmation) {
            case "y":
            case "Y":
            case "yes":
            case "YES":
            case "Yes":

                System.out.print("Please enter the answer text for the flashcard:\n> ");
                String answerText = consoleReader.readLine();

                System.out.println("You provided: \n\n" + answerText + "\n\n" + "Is this correct? (y/n)");
                confirmation = consoleReader.readLine();

                switch (confirmation) {
                    case "y":
                    case "Y":
                    case "yes":
                    case "YES":
                    case "Yes":
                        Flashcard newFlashcard = new Flashcard(creatorUsername, questionText, answerText);
                        FileWriter dataWriter = new FileWriter("database/flashcards.txt", true);
                        dataWriter.write(newFlashcard.toFileString());
                        dataWriter.close();
                        break;
                    default:
                        System.out.println("Confirmation check failed. Navigating back to dashboard...");
                }

                break;

            default:
                System.out.println("Confirmation check failed. Navigating back to dashboard...");
        }

    }

    public static void viewFlashcards(BufferedReader consoleReader, String ownerUsername) throws IOException {

        System.out.println("You selected: View my flashcards");

        BufferedReader dataReader = new BufferedReader(new FileReader("database/flashcards.txt"));
        LinkedList<Flashcard> flashcards = new LinkedList<>();

        String dataCursor;
        while ((dataCursor = dataReader.readLine()) != null) {
            String[] cardData = dataCursor.split("::");
            if (cardData[0].equals(ownerUsername)) {
                flashcards.add(new Flashcard(cardData[0], cardData[1], cardData[2]));
            }
        }

        System.out.println(ownerUsername + "'s Flashcards: \n\n");
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard card = flashcards.get(i);
            System.out.printf("Card %d:\n\tQuestion: %s\n\tAnswer: %s\n\n", (i + 1), card.getQuestionText(), card.getAnswerText());
        }

        System.out.print("Press any key and then enter to return to the dashboard.\n> ");
        consoleReader.readLine();

    }

}
