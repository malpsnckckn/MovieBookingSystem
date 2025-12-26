package com.moviebooking.services;

import com.moviebooking.models.User;
import com.moviebooking.models.Reservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    // Load users from CSV file
    public void loadUsersFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 2) { // username,password
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    users.add(new User(username, password));
                }
            }

            System.out.println("Users loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading users file: " + e.getMessage());
        }
    }

    // Find user by username
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Login control
    public User login(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    // Add a reservation for a specific user
    public void addReservationForUser(String username, Reservation reservation) {
        User user = getUserByUsername(username);
        if (user != null) {
            user.addReservation(reservation);
        }
    }

    // Get reservation history for a user
    public List<Reservation> getUserReservations(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getReservationHistory();
        }
        return new ArrayList<>();
    }

    // Register a new user and save to CSV
    public void registerUser(String username, String password, String filePath) {
        User existingUser = getUserByUsername(username);
        if (existingUser != null) {
            System.out.println("Username already exists.");
            return;
        }

        User newUser = new User(username, password);
        users.add(newUser);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(username + "," + password);
        } catch (Exception e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }

        System.out.println("User " + username + " registered successfully.");
    }

    // Get all users
    public List<User> getAllUsers() {
        return users;
    }
}
