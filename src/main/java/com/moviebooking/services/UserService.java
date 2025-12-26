package com.moviebooking.services;

import com.moviebooking.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }
    // Load users from CSV file
    public void loadUsersFromFile(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null){
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 2) {
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
    //find username
    public User getUserByUsername(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    // Login control
    public User login(String username, String password){
        User user = getUserByUsername(username);
        if (user != null && user.checkPassword(password)){
            return user;
        }
        return null;
    }

//debugin / testing
    public List<User> getAllUsers(){
        return users;
    }
}
