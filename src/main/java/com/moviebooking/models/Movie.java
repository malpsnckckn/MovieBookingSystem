package com.moviebooking.models;
/**
 * Abstract base class for movies.
 * Defines common movie properties and behavior.
 */
public abstract class Movie {
    protected String title;
    protected String genre;
    protected int durationMinutes;
    protected double basePrice;    
 /**
* Creates a movie with basic information.
*/
    public Movie(String title, String genre, int durationMinutes, double basePrice){
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.basePrice = basePrice;
    }
/**
* Calculates final ticket price.
* Implemented differently by subclasses (2D / 3D).
*/
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

