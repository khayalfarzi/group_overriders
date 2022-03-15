package az.iktlab;

import az.iktlab.controller.BookingController;
import az.iktlab.controller.FlightController;
import az.iktlab.dao.repo.BookingDao;
import az.iktlab.dao.repo.FlightDao;
import az.iktlab.model.Flight;
import az.iktlab.service.BookingService;
import az.iktlab.service.FlightService;
import az.iktlab.util.*;
import az.iktlab.util.Commands.AppMenuCommands;
import az.iktlab.util.Commands.SearchBookingCommands;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void runApplication(String strUsername) throws SQLException {
        final Scanner sc = new Scanner(System.in);
        final String username = strUsername;
        System.out.printf(ConsoleColors.RESET + "\"%s\" welcome to the application menu:\n\n",
                username);

        FlightDao flightDao = new FlightDao();
        FlightService flightService = new FlightService(flightDao);
        FlightController flightController = new FlightController(flightService);

        BookingDao bookingDao = new BookingDao();
        BookingService bookingService = new BookingService(bookingDao);
        BookingController bookingController = new BookingController(bookingService);


        while (true) {

            CommandLineHelper.showAppMenuBar();

            System.out.print(ConsoleColors.RESET + "Select command:");

            String commandNumber = sc.nextLine();
            AppMenuCommands command = Validator.getAppMenuCommandName(commandNumber);
            if (command != null) {
                switch (command) {
                    case ONLINE_BOARD:
                        flightController.getAllNext24Flights().forEach(System.out::println);
                        System.out.println();
                        break;
                    case SHOW_FLIGHT_INFO:
                        System.out.print("\nInformation about the flight whose ID is entered:\n");
                        long flightId = flightController.checkFlightIdInDatabase();
                        if (flightId > 0) {
                            System.out.println(ConsoleColors.GREEN +
                                    "Result corresponding to the entered id:");
                            System.out.println(ConsoleColors.RESET +
                                    flightController.showInfoFlight(flightId) + "\n");
                        } else {
                            System.out.println(ConsoleColors.RED +
                                    "No flight found matching the ID entered.\n");
                        }
                        break;
                    case SEARCH_AND_BOOKING:
                        System.out.println("\nSearch and Booking flights menu:");
                        CommandLineHelper.showSearchAndBookingMenuBar();
                        System.out.print(ConsoleColors.RESET + "Select command:");
                        String commandNumberSB = sc.nextLine();
                        SearchBookingCommands commandSB = Validator.getSearchBookingCommandName(commandNumberSB);
                        if (commandSB != null) {
                            switch (commandSB) {
                                case SEARCH:
                                    List<Flight> checkList = flightController.searchFlight();
                                    if (checkList.isEmpty() == true) {
                                        break;
                                    } else {
                                        checkList.forEach(System.out::println);
                                        System.out.println();
                                    }
                                    break;
                                case BOOKING:
                                    long bookingFlightId = bookingController.bookingFlight(username);
                                    if (bookingFlightId > 0)
                                        flightController.emptySeatsDecrease(bookingFlightId);
                                    break;
                            }
                        } else {
                            System.out.printf(ConsoleColors.RED + "%s is invalid Command!\n\n", commandNumberSB);
                        }
                        break;
                    case CANCEL_BOOKING:
                        long cancelBookingFlightId = bookingController.cancelBooking();
                        if (cancelBookingFlightId > 0)
                            flightController.emptySeatsIncrease(cancelBookingFlightId);
                        break;
                    case MY_FLIGHTS:
                        System.out.print(ConsoleColors.RESET +
                                "A list of all flights booked by the user:\n");
                        int countUserFlight = bookingController.checkMyBookings(username);
                        if (countUserFlight > 0) {
                            System.out.print(ConsoleColors.GREEN);
                            bookingController.showMyBookings(username)
                                    .forEach(System.out::println);
                            System.out.print("\n");
                        } else {
                            System.out.println(ConsoleColors.RED +
                                    "There are no flights you have booked\n");
                        }
                        break;
                    case LOGOUT:
                        System.out.println(ConsoleColors.GREEN + "You are logged out of your account\n");
                        return;
                }
            } else {
                System.out.printf(ConsoleColors.RED + "%s is invalid Command!\n\n", commandNumber);
            }
        }
    }
}
