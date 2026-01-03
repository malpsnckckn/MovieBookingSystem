package com.moviebooking.models;
/**
 * Represents a snack that can be purchased with a reservation.
 */
public class Snack {
    private String name; // snack name
    private double price; // snack price
    public Snack(String name, double price){
        this.name = name;
        this.price = price;
    }
/**
* Creates a snack with name and price.
*/
    public String getName() {
        return name;
    }
    public double getPrice(){
        return price;
    }
/**
* Returns snack info for menu display.
*/
    @Override
    public String toString(){
        return name + " (" + price + " $)";
    }
}
