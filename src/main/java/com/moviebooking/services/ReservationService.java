package com.moviebooking.services;

import com.moviebooking.models.*;

import java.util.*;

public class ReservationService {

    private final ShowTimeService showTimeService;
    private final SnackService snackService;

    public ReservationService() {
        this.showTimeService = new ShowTimeService();
        this.snackService = new SnackService();
    }
//start the rezervation
    public void makeReservation(Scanner scanner, User user) {
//select day
        String day = selectDay(scanner);
        if (day == null) return;

        List<ShowTime> showTimes = showTimeService.loadShowTimes(day);

        if (showTimes.isEmpty()) {
            System.out.println("No showtimes available.");
            return;
        }
// view session
        for (int i = 0; i < showTimes.size(); i++) {
            ShowTime st = showTimes.get(i);
            System.out.println((i + 1) + ". "
                    + st.getMovie().getTitle()
                    + " (" + st.getMovie().getGenre() + ") at "
                    + st.getTime());
        }

        System.out.print("Select showtime: ");
        int showChoice = scanner.nextInt();
        scanner.nextLine();

        if (showChoice < 1 || showChoice > showTimes.size()) {
            System.out.println("Invalid showtime.");
            return;
        }

        ShowTime selectedShowTime = showTimes.get(showChoice - 1);
        selectedShowTime.printSeatingPlan();

        System.out.print("Enter seat (A1): ");
        String seatId = scanner.nextLine();
//show us setting plan
        Reservation reservation =
                selectedShowTime.createReservation(seatId);

        if (reservation == null) {
            System.out.println("Seat already taken or invalid.");
            return;
        }

        user.addReservation(reservation);

        List<String> snacks = new ArrayList<>();
        double snackTotal = 0;

        while (true) {
            snackService.printSnackMenu();
            int snackChoice = scanner.nextInt();
            scanner.nextLine();

            if (snackChoice == 0) break;

            Snack snack = snackService.getSnackByIndex(snackChoice);
            if (snack != null) {
                snacks.add(snack.getName());
                snackTotal += snack.getPrice();
            }
        }
// calculate total price
        double totalPrice =
                selectedShowTime.calculateBasePrice() + snackTotal;

        ReservationHistoryService.saveReservation(
                user.getUsername(),
                day,
                reservation,
                snacks,
                totalPrice
        );

        showTimeService.saveShowTimes(day, showTimes);

        System.out.println("\nReservation completed!");
        System.out.println("Total price: " + totalPrice + " $");
    }
    private String selectDay(Scanner scanner) {

        String[] days = {
                "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"
        };

        System.out.println("\nSelect a day:");
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + days[i]);
        }

        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > 7) {
            System.out.println("Invalid day.");
            return null;
        }
        return days[choice - 1];
    }
}
