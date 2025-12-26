package com.moviebooking.models;
import java.util.ArrayList;
import java.util.List;;
public class User {
    private String username;
    private String password;
    private List<Reservation> reservationHistory;

    public User( String username, String password){
        this.username = username;
        this.password = password;
        this.reservationHistory = new ArrayList<>();
    }
    public String getUsername(){
        return username;
    }
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    public void addReservation(Reservation reservation){
        reservationHistory.add(reservation);
    }
    public List<Reservation> getReservationHistory(){
        return reservationHistory;
    }
    @Override
    public String toString(){
        return "User: " + username +
               " | Reservations: " + reservationHistory.size();
    }
    
}
