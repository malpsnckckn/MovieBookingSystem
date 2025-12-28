package com.moviebooking.services;

import com.moviebooking.models.Reservation;
import com.moviebooking.models.Seat;

import java.io.*;
import java.util.*;

public class ReservationHistoryService {

    private static final String FILE_PATH = "data/ReservationHistory.csv";

    public static void saveReservation(
            String username,
            String day,
            Reservation reservation,
            List<String> snacks,
            double totalPrice
    ) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            String snackText = snacks.isEmpty()
                    ? "None"
                    : String.join(" | ", snacks);

            for (Seat seat : reservation.getSeats()) {
                writer.write(
                        username + "," +
                        day + "," +
                        reservation.getShowTime().getMovie().getTitle() + "," +
                        reservation.getShowTime().getTime() + "," +
                        seat.getSeatNumber() + "," +
                        snackText + "," +
                        totalPrice
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving reservation history: " + e.getMessage());
        }
    }
    public static void printUserHistory(String username) {

    List<String> history = getUserHistory(username);

    if (history.isEmpty()) {
        System.out.println("No reservations found.");
        return;
    }

    System.out.println("==== Reservation History ====");
    history.forEach(System.out::println);
    }

    public static List<String> getUserHistory(String username) {
        List<String> history = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts[0].equals(username)) {
                    history.add(
                            "Day: " + parts[1] +
                            " | Movie: " + parts[2] +
                            " | Time: " + parts[3] +
                            " | Seat: " + parts[4] +
                            " | Snacks: " + parts[5] +
                            " | Total: " + parts[6] + " ₺"
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading reservation history: " + e.getMessage());
        }
        return history;
    }
}
