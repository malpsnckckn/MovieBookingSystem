package com.moviebooking.models;
/**
 * Represents a 3D movie
 * Includes extra cost for 3D glasses
 */
public class Movie3D extends Movie{
//create 3D movie 
    private static final double glasses_price = 25.0;
    public Movie3D(String title, String genre, int durationMinute, double basePrice){
        super(title, genre, durationMinute, basePrice);
    }
//return ticket price including glasses price
    @Override
    public double getTicketPrice(){
        return basePrice + glasses_price;
    }
}
