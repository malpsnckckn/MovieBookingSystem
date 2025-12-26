package com.moviebooking.interfaces;

public interface Bookable {
    boolean bookSeat(String seatNumber);
    double calculatePrice();
}
