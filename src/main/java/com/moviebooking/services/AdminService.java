package com.moviebooking.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminService {

    public void adminMenu(Scanner scanner) {

        boolean running = true;

        while (running) {

            System.out.println("\n=== ADMIN PANEL ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Logout");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> addMovie(scanner);

                case 2 -> {
                    running = false;
                    System.out.println("Admin logged out.");
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addMovie(Scanner scanner) {

        String day = selectDay(scanner);
        if (day == null) return;

        System.out.print("Movie title: ");
        String title = scanner.nextLine();

        System.out.print("Movie type (2D / 3D): ");
        String genre = scanner.nextLine();

        System.out.print("Duration (minutes): ");
        int duration = scanner.nextInt();

        System.out.print("Base price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Showtime (HH:mm): ");
        String time = scanner.nextLine();

        String filePath = "data/" + day.toLowerCase() + ".csv";

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {

            StringBuilder sb = new StringBuilder();
            sb.append(title).append(",")
              .append(genre).append(",")
              .append(duration).append(",")
              .append(price).append(",")
              .append(time);

            for (char r = 'A'; r <= 'E'; r++) {
                for (int i = 1; i <= 10; i++) {
                    sb.append(",available");
                }
            }

            writer.write(sb.toString());
            writer.newLine();

            System.out.println("✅ Movie added successfully to " + day);

        } catch (IOException e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    private String selectDay(Scanner scanner) {

        String[] days = {
                "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"
        };

        System.out.println("\nSelect a day:");
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + days[i]);
        }

        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > 7) {
            System.out.println("Invalid day.");
            return null;
        }

        return days[choice - 1];
    }
}
