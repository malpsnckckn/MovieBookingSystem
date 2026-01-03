package com.moviebooking.models;
/**
 * Represents a single seat in the cinema hall.
 */
public class Seat {
    private String seatNumber; // seat id
    private boolean isReserved; // reservation status
/**
* Creates a seat with given seat number.
*/
    public Seat(String seatNumber){
        this.seatNumber = seatNumber;
        this.isReserved = false;
    }
/**
* Reserves the seat if available.
*/
    public boolean reserve(){
        if(!isReserved){
            isReserved = true;
            return true;
        }
        return false;
    }
    public void free(){
        this.isReserved = false;
    }
    public String getSeatNumber(){
        return seatNumber;
    }
    public boolean isReserved(){
        return isReserved;
    }
}
