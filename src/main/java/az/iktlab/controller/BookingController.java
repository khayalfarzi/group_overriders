package az.iktlab.controller;

import az.iktlab.dao.entity.BookingEntity;
import az.iktlab.mapper.BookingMapper;
import az.iktlab.model.Booking;
import az.iktlab.model.Gender;
import az.iktlab.service.BookingService;
import az.iktlab.util.Validator;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookingController {
    private final Scanner sc = new Scanner(System.in);

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    public int checkMyBookings(String username) {
        return service.checkMyBookings(username);
    }

    public List<Booking> showMyBookings(String username) throws SQLException {
        return service.showMyBookings(username);
    }

    public Long bookingFlight(String username) {
        System.out.print("\nFill in the fields to booking for flights:\n");
        Booking booking = new Booking();
        booking.setUsername(username);

        System.out.print("Enter flight id:");
        Long flightId = sc.nextLong();
        sc.nextLine();
        booking.setFlightId(flightId);

        System.out.print("Enter passenger name:");
        String passengerName = sc.nextLine();
        booking.setPassengerName(passengerName);

        System.out.print("Enter passenger surname:");
        String passengerSurname = sc.nextLine();
        booking.setPassengerSurname(passengerSurname);

        System.out.print("(Example for gender:male/female) Enter gender:");
        String gender = sc.nextLine();
        booking.setGender(Validator.validateGender(gender));

        return service.bookingFlight(booking);
    }

    public int checkPassengerBookingTable(Booking booking) {
        return service.checkPassengerBookingTable(booking);
    }

    public int checkBookingIdInDatabase(long bookingId) {
        return service.checkBookingIdInDatabase(bookingId);
    }

    public List<Booking> getFlightIdCancelBooking(long bookingId) {
        return service.getFlightIdCancelBooking(bookingId);
    }

    public long cancelBooking() {
        System.out.print("\nFill in the fields to cancel for booking:\n");
        System.out.print("Enter booking id:");
        Long bookingId = sc.nextLong();

        return service.cancelBooking(bookingId);
    }

}
