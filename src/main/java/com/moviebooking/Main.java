package com.moviebooking;

import com.moviebooking.models.User;
import com.moviebooking.services.*;

import java.util.Scanner;

public class Main {

    private static final String USERS_CSV = "data/users.csv";

    public static void main(String[] args) {
        startApplication();
    }
//start application
    private static void startApplication() {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        userService.loadUsersFromFile(USERS_CSV);

        ReservationService reservationService = new ReservationService();
        AdminService adminService = new AdminService();

        boolean running = true;

        System.out.println("================================");
        System.out.println("  Movie Booking System Started");
        System.out.println("================================");

        while (running) {

            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
//login user 
                case 1 -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    User loggedInUser = userService.login(username, password);

                    if (loggedInUser != null) {
                        System.out.println("Login successful!");

                        if (loggedInUser.isAdmin()) {
                            adminService.adminMenu(scanner);
                        } else {
                            userMenu(scanner, loggedInUser, reservationService);
                        }

                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
//new user
                case 2 -> registerUser(scanner, userService);
//exit
                case 3 -> {
                    running = false;
                    System.out.println("Exiting system...");
                }

                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void userMenu(
            Scanner scanner,
            User user,
            ReservationService reservationService
    ) {

        boolean userMenuRunning = true;

        while (userMenuRunning) {

            System.out.println("\nWelcome, " + user.getUsername());
            System.out.println("1. Make a Reservation");
            System.out.println("2. View Reservation History");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
//reservation 
                case 1 -> reservationService.makeReservation(scanner, user);
//show reservation history
                case 2 -> ReservationHistoryService
                        .printUserHistory(user.getUsername());
//logout
                case 3 -> {
                    userMenuRunning = false;
                    System.out.println("Logged out.");
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }
//new user register
    private static void registerUser(
            Scanner scanner,
            UserService userService
    ) {

        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
//check the same username 
        if (userService.getUserByUsername(username) != null) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String p1 = scanner.nextLine();
//check the password 
        System.out.print("Confirm password: ");
        String p2 = scanner.nextLine();

        if (!p1.equals(p2)) {
            System.out.println("Passwords do not match.");
            return;
        }

        userService.addUser(new User(username, p1));
        System.out.println("User registered successfully!");
    }
}
