package com.moviebooking;

import com.moviebooking.services.UserService;
import com.moviebooking.models.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        startApplication();
    }

    private static void startApplication() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        String userCsvPath = "data/users.csv";
        userService.loadUsersFromFile(userCsvPath);

        boolean running = true;

        System.out.println("================================");
        System.out.println("      Movie Booking System ");
        System.out.println("================================");

        while (running) {
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = scanner.nextLine();

                    User loggedInUser = userService.login(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("Login successful! Welcome, " + loginUsername + "!");
                    } else {
                        System.out.println("Login failed. Invalid username or password.");
                    }
                    break;
                case 2:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password1 = scanner.nextLine();
                    System.out.print("Confirm password: ");
                    String password2 = scanner.nextLine();
                    if (!password1.equals(password2)) {
                        System.out.println("Passwords do not match. Try again.");
                    } else {
                        userService.registerUser(newUsername, password1, userCsvPath);
                    }
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
