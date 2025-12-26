package com.moviebooking.models;

public class Snack {
    private String name;
    private double price;
    public Snack(String name, double price){
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return name + " (" + price + " $)";
    }
}
