package com.moviebooking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        startApplication();
    }

    private static void startApplication(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("================================");
        System.out.println("  Movie Booking System Started");
        System.out.println("================================");

        while (running) {
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Login selected.");
                    break;
                case 2:
                    System.out.println("Register selected.");
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
