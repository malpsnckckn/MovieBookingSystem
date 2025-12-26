package com.moviebooking.models;

import java.util.ArrayList;
import java.util.List;

public class ShowTime {
    private Movie movie;
    private String time;
    private List<Seat> seats;
    public ShowTime(Movie movie, String time) {
        this.movie = movie;
        this.time = time;
        this.seats = new ArrayList<>();
        initializeSeats();
    }
    private void initializeSeats() {
        String[] rows = {"A", "B", "C", "D", "E"};
        for (String row : rows) {
            for (int i = 1; i <= 10; i++) {
                String seatId = row + i;
                seats.add(new Seat(seatId));
            }
        }
    }
    public void printSeatingPlan() {
        System.out.println("Seating plan for: " + movie.getTitle() + " (" + time + ")");
        System.out.println("-------------------------------");
        
        int counter = 0;
        for (Seat seat : seats) {
            String status = seat.isReserved() ? "[X]" : "[" + seat.getSeatNumber() + "]";
            System.out.print(status + "  ");
            
            counter++;
            if (counter % 10 == 0) {
                System.out.println();
                System.out.println();
            }
        }
    }
    public boolean reserveSeat(String seatId) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatId)) {
                if (!seat.isReserved()) {
                    seat.reserve(); 
                    System.out.println("Seat " + seatId + " booked successfully");
                    return true;
                } else {
                    System.out.println("Seat " + seatId + " is already taken");
                    return false;
                }
            }
        }
        System.out.println("Invalid Seat ID: " + seatId);
        return false;
    }
    public Movie getMovie() {
        return movie;
    }
}