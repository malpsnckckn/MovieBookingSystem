package com.moviebooking.models;

import com.moviebooking.interfaces.Bookable;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a movie showtime with seats and booking operations.
 */
public class ShowTime implements Bookable {

    private Movie movie; // movie played in this show time
    private String time; // showtime(HH:mm)
    private List<Seat> seats; //seating plan
/**
* Creates a showtime and initializes seats.
*/
    public ShowTime(Movie movie, String time) {
        this.movie = movie;
        this.time = time;
        this.seats = new ArrayList<>();
        initializeSeats();
    }
/**
* Initializes seat layout (A–E, 1–10).
*/
    private void initializeSeats() {
        String[] rows = {"A", "B", "C", "D", "E"};
        for (String row : rows) {
            for (int i = 1; i <= 10; i++) {
                seats.add(new Seat(row + i));
            }
        }
    }
/**
* Prints seating plan with reservation status.
*/
    public void printSeatingPlan() {
        System.out.println("Seating plan for: "
                + movie.getTitle() + " (" + time + ")");
        System.out.println("---------------[SCREEN]---------------");                                            

        int counter = 0;
        for (Seat seat : seats) {
            String status = seat.isReserved()
                    ? "[X]"
                    : "[" + seat.getSeatNumber() + "]";
            System.out.print(status + "  ");

            counter++;
            if (counter % 10 == 0) {
                System.out.println("\n");
            }
        }
    }
/**
* Reserves a seat if available.
*/
    @Override
    public boolean bookSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equalsIgnoreCase(seatNumber)
                    && !seat.isReserved()) {
                seat.reserve();
                return true;
            }
        }
        return false;
    }
    @Override
    public Reservation createReservation(String seatNumber) {

        if (!bookSeat(seatNumber)) {
            return null; // seat unavaliable
        }

        Reservation reservation = new Reservation(this);

        Seat seat = seats.stream()
                .filter(s -> s.getSeatNumber().equalsIgnoreCase(seatNumber))
                .findFirst()
                .orElse(null);

        if (seat != null) {
            reservation.addSeat(seat);
        }

        return reservation;
    }

    @Override
    public double calculateBasePrice() {
        return movie.getTicketPrice();
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getTime() {
        return time;
    }
}
