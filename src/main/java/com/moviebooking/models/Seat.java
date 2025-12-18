package main.java.com.moviebooking.models;

public class Seat {
    private String seatNumber;
    private boolean isReserved;

    public Seat(String seatNumber){
        this.seatNumber = seatNumber;
        this.isReserved = false;
    }
    public void reserve(){
        if(!isReserved){
            this.isReserved = true;
        }else {
            System.out.println("Seat is full!");
        }
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
