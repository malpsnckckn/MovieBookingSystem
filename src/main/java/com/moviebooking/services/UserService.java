package com.moviebooking.services;

import com.moviebooking.models.User;
import com.moviebooking.models.Reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
// all user registered in system
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public void loadUsersFromFile(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
//skip the first (header)line
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String username = parts[0].trim();
                String password = parts[1].trim();
                String role = parts.length == 3 ? parts[2].trim() : "USER";

                users.add(new User(username, password, role));
            }
        }

    } catch (IOException e) {
        System.out.println("Error loading users file: " + e.getMessage());
    }
}


    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User login(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
//add reservation (user)
    public void addReservationForUser(String username, Reservation reservation) {
        User user = getUserByUsername(username);
        if (user != null) {
            user.addReservation(reservation);
        }
    }

    public List<Reservation> getUserReservations(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getReservationHistory();
        }
        return new ArrayList<>();
    }

    public void registerUser(String username, String password, String filePath) {
        if (getUserByUsername(username) != null) {
            System.out.println("Username already exists.");
            return;
        }

        User newUser = new User(username, password, "USER");
        users.add(newUser);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(username + "," + password + ",USER");
        } catch (Exception e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }

        System.out.println("User " + username + " registered successfully.");
    }

    public void addUser(User user) {
        users.add(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.csv", true))) {
            
            writer.write(
                user.getUsername() + "," +
                user.getPasswordForSaving() + "," +
                user.getRole()  
            );
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        return users;
    }
}
