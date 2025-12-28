package com.moviebooking.interfaces;

import com.moviebooking.models.Reservation;

public interface Bookable {
    boolean bookSeat(String seatNumber);
    Reservation createReservation(String seatNumber);
    double calculateBasePrice();
}
