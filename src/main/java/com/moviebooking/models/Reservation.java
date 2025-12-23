package main.java.com.moviebooking.models;
import java.util.ArrayList;
import java.util.List;
public class Reservation {
    private ShowTime showTime;
    private List<Seat> reservedSeats;
    private List<Snack> snacks;
    private double totalPrice;

    public Reservation(ShowTime showTime){
        this.showTime = showTime;
        this.reservedSeats = new ArrayList<>();
        this.snacks = new ArrayList<>();
        this.totalPrice = 0.0;
    }
    public void addSeat(Seat seat){
        if(!seat.isReserved()){
            seat.reserve();
            reservedSeats.add(seat);
            totalPrice += showTime.getMovie().getTicketPrice();
        }
    }
    public void addSnack(Snack snack){
        snacks.add(snack);
        totalPrice += snack.getPrice();
    }
    public double getTotalPrice(){
        return totalPrice;
    }
    public List<Seat> getReservedSeats(){
        return reservedSeats;
    }
    public List<Snack> getSnacks(){
        return snacks;
    }
    public ShowTime getShowTime(){
        return showTime;
    }
    @Override
    public String toString(){
        return "Movie: " + showTime.getMovie().getTitle() +
                ", Time: " + showTime +
                ", Seats: " + reservedSeats.size() + 
                ", Total: " + totalPrice + "$";
    }
}
