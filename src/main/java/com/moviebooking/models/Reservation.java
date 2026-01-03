package com.moviebooking.models;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a reservation made by a user for a specific showtime.
 */
public class Reservation {

    private ShowTime showTime; //related show time
    private List<Seat> seats; //reserved seat
/**
* Creates a reservation for the given showtime.
*/
    public Reservation(ShowTime showTime) {
        this.showTime = showTime;
        this.seats = new ArrayList<>();
    }
/**
* Adds a seat to the reservation.
*/
    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }
/**
* Returns reservation details for display.
*/
    @Override
    public String toString() {
        return "Movie: " + showTime.getMovie().getTitle() +
                " | Time: " + showTime.getTime() +
                " | Seats: " + seats;
    }
}
