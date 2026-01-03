package com.moviebooking.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private String role;
    private List<Reservation> reservationHistory;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.reservationHistory = new ArrayList<>();
    }

    public User(String username, String password) {
        this(username, password, "USER");
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordForSaving() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getRole() {
        return role;
    }
// check admin
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }

    public void addReservation(Reservation reservation) {
        reservationHistory.add(reservation);
    }

    public List<Reservation> getReservationHistory() {
        return reservationHistory;
    }

    @Override
    public String toString() {
        return "User: " + username +
               " | Role: " + role +
               " | Reservations: " + reservationHistory.size();
    }
}
