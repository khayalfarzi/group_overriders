package az.iktlab.controller;

import az.iktlab.model.Booking;
import az.iktlab.model.Flight;
import az.iktlab.service.FlightService;
import az.iktlab.util.ConsoleColors;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class FlightController {
    private final Scanner sc = new Scanner(System.in);

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    public int getCountSearchResult(Flight flight) {
        return service.getCountSearchResult(flight);
    }

    public int countEmptySeats(long flightId) {

        return service.checkEmptySeats(flightId);
    }

    public List<Flight> searchFlight() throws SQLException {
        System.out.print("Fill in the fields to search for flights:\n");
        Flight flight = new Flight();
        System.out.print("Enter Destination From:");
        String destinationFrom = sc.next();
        flight.setDestinationFrom(destinationFrom);

        System.out.print("Enter Destination TO:");
        String destinationTo = sc.next();
        flight.setDestinationTO(destinationTo);

        System.out.println("\"Date example: 2022-01-25 (yyyy-MM-dd)\":");
        System.out.print("Enter date:");
        String sqlDate = sc.next();
        flight.setDate(Date.valueOf(sqlDate));

        System.out.println("\"Time example: 23:10 (HH:mm)\":");
        DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm");
        System.out.print("Enter time:");
        String timeString = sc.next();
        LocalTime sqlTime = LocalTime.parse(timeString, parseFormat);
        flight.setTime(Time.valueOf(sqlTime));

        System.out.print("Enter the number of people:");
        int numberOfPeople = sc.nextInt();
        sc.nextLine();
        flight.setEmptySeats(numberOfPeople);

        return service.searchFlight(flight);

    }

    public List<Flight> getAllNext24Flights() throws SQLException {
        LocalDate date = LocalDate.now();
        LocalDate nextDate = date.plusDays(1);
        System.out.println(ConsoleColors.GREEN + "All flights in the next 24 hours.");
        System.out.print(ConsoleColors.RESET);

        return service.getAllNext24Flights(date, nextDate);
    }

    public List<Flight> showInfoFlight(long flightId) throws SQLException {
        return service.showInfoFlight(flightId);
    }

    public void emptySeatsDecrease(long flightId) {
        service.emptySeatsDecrease(flightId);
    }

    public void emptySeatsIncrease(long flightId) {
        service.emptySeatsIncrease(flightId);
    }

    public long checkFlightIdInDatabase() {
        System.out.print(ConsoleColors.RESET + "Enter flight id:");
        long flightId = sc.nextLong();

        return service.checkFlightIdInDatabase(flightId);
    }
}
