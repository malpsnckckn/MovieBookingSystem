package main.java.com.moviebooking.models;

public class Movie2D extends Movie {
    public Movie2D(String title, String genre, int durationMinute, double basePrice){
        super(title, genre, durationMinute, basePrice);
    }
    @Override
    public double getTicketPrice(){
        return basePrice;
    }
}
