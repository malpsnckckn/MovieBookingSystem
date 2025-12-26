package com.moviebooking.models;

public class Movie3D extends Movie{
    private static final double glasses_price = 25.0;
    public Movie3D(String title, String genre, int durationMinute, double basePrice){
        super(title, genre, durationMinute, basePrice);
    }
    @Override
    public double getTicketPrice(){
        return basePrice + glasses_price;
    }
}
