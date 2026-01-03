package com.moviebooking.models;
/**
 * Represents a standard 2D movie.
 * Uses base ticket price without extra cost.
 */
public class Movie2D extends Movie {
// create 2D movie
    public Movie2D(String title, String genre, int durationMinute, double basePrice){
        super(title, genre, durationMinute, basePrice);
    }
// return ticket price for 2D movie
    @Override
    public double getTicketPrice(){
        return basePrice;
    }
}
