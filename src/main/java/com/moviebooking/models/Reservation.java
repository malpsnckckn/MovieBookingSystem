package com.moviebooking.models;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private ShowTime showTime;
    private List<Seat> seats;

    public Reservation(ShowTime showTime) {
        this.showTime = showTime;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Movie: " + showTime.getMovie().getTitle() +
                " | Time: " + showTime.getTime() +
                " | Seats: " + seats;
    }
}
