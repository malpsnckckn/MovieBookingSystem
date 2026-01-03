package com.moviebooking.interfaces;

import com.moviebooking.models.Reservation;
/**
 * Defines booking-related operations for a showtime.
 * Implementing classes must handle seat booking
 * and reservation creation.
 */
public interface Bookable {
    boolean bookSeat(String seatNumber);
    Reservation createReservation(String seatNumber);
    double calculateBasePrice();
}
