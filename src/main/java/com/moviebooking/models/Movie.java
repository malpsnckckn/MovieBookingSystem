package com.moviebooking.models;

public abstract class Movie {
    protected String title;
    protected String genre;
    protected int durationMinutes;
    protected double basePrice;    

    public Movie(String title, String genre, int durationMinutes, double basePrice){
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.basePrice = basePrice;
    }
    public abstract double getTicketPrice();
    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
    public int getDurationMinutes(){
        return durationMinutes;
    }
}

