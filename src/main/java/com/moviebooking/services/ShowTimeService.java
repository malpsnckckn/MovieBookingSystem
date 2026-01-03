package com.moviebooking.services;

import com.moviebooking.models.*;

import java.io.*;
import java.util.*;

public class ShowTimeService {

    private static final String DATA_PATH = "data/";

    public List<ShowTime> loadShowTimes(String day) {

        List<ShowTime> showTimes = new ArrayList<>();
        String filePath = DATA_PATH + day + ".csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // header
            String line;

            while ((line = reader.readLine()) != null) {

                String[] p = line.split(",");
//A Movie object is created (polymorphism)
                Movie movie = p[1].equalsIgnoreCase("3D")
                        ? new Movie3D(
                                p[0],
                                p[1],
                                Integer.parseInt(p[2]),
                                Double.parseDouble(p[3])
                        )
                        : new Movie2D(
                                p[0],
                                p[1],
                                Integer.parseInt(p[2]),
                                Double.parseDouble(p[3])
                        );
//session created using movie and time information
                ShowTime st = new ShowTime(movie, p[4]);

                List<Seat> seats = st.getSeats();
                for (int i = 0; i < seats.size(); i++) {
                    if (p.length > 5 + i && p[5 + i].equalsIgnoreCase("taken")) {
                        seats.get(i).reserve();
                    }
                }

                showTimes.add(st);
            }

        } catch (IOException e) {
            System.out.println("Error loading showtimes: " + e.getMessage());
        }

        return showTimes;
    }

    public void saveShowTimes(String day, List<ShowTime> showTimes) {

        String filePath = DATA_PATH + day + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("title,genre,durationMinutes,basePrice,time");
            for (char r = 'A'; r <= 'E'; r++) {
                for (int i = 1; i <= 10; i++) {
                    writer.write("," + r + i);
                }
            }
            writer.newLine();

            for (ShowTime st : showTimes) {

                StringBuilder sb = new StringBuilder();
                sb.append(st.getMovie().getTitle()).append(",")
                  .append(st.getMovie().getGenre()).append(",")
                  .append(st.getMovie().getDurationMinutes()).append(",")
                  .append(st.getMovie().getTicketPrice()).append(",")
                  .append(st.getTime());

                for (Seat seat : st.getSeats()) {
                    sb.append(",").append(seat.isReserved() ? "taken" : "available");
                }

                writer.write(sb.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving showtimes: " + e.getMessage());
        }
    }
}
