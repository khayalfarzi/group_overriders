package az.iktlab.service;

import az.iktlab.dao.entity.BookingEntity;
import az.iktlab.dao.repo.BookingDao;
import az.iktlab.mapper.BookingMapper;
import az.iktlab.model.Booking;
import az.iktlab.util.ConsoleColors;
import az.iktlab.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private final BookingDao dao;

    public BookingService(BookingDao dao) {
        this.dao = dao;
    }

    public int checkMyBookings(String username) {
        int count = 0;
        try {
            count = dao.checkMyBookings(username);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while count bookings.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
        return count;
    }

    public List<Booking> showMyBookings(String username) {

        List<BookingEntity> bookings = new ArrayList<>();
        try {
            bookings = dao.showMyBookings(username);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while show bookings.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }

        return BookingMapper.mapToDto(bookings);
    }

    public Long bookingFlight(Booking booking) {
        if (Validator.checkFlightIdInDatabase(booking.getFlightId()) == false) return 0l;
        if (Validator.checkFirstAndLastName(booking.getPassengerName()) == false) return 0l;
        if (Validator.checkFirstAndLastName(booking.getPassengerSurname()) == false) return 0l;
        if (Validator.checkGender(booking.getGender()) == false) return 0l;
        if (Validator.checkPassengerBookingTable(booking) == false) return 0l;
        if (Validator.checkEmptySeats(booking.getFlightId()) == false) return 0l;


        BookingEntity bookingEntity = BookingMapper.mapToEntity(booking);
        try {
            dao.bookingFlight(bookingEntity);
            System.out.println(ConsoleColors.GREEN + "Booking successfully.\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ConsoleColors.RED + "Error occurred while show bookings");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }

        return booking.getFlightId();
    }

    public int checkPassengerBookingTable(Booking booking) {
        BookingEntity bookingEntity = BookingMapper.mapToEntity(booking);
        int count = 0;
        try {
            count = dao.checkPassengerBookingTable(bookingEntity);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while bookings.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
        if (count > 0) {
            System.out.printf(ConsoleColors.RED +
                            "A passenger named %s %s has a reservation for a flight with flight id %s.\n\n",
                    booking.getPassengerName(),
                    booking.getPassengerSurname(), booking.getFlightId());
            return count;
        } else return 0;
    }

    public int checkBookingIdInDatabase(long bookingId) {
        int count = 0;
        try {
            count = dao.checkBookingIdInDatabase(bookingId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while check booking id.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
        return count;
    }

    public List getFlightIdCancelBooking(long bookingId) {
        List<BookingEntity> bookings = new ArrayList<>();

        try {
            bookings = dao.getFlightIdCancelBooking(bookingId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "Error occurred while get flight id in bookings table.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
        return BookingMapper.mapToDto(bookings);
    }

    public long cancelBooking(long bookingId) {
        if (Validator.checkBookingId(bookingId) == false) return 0l;
        long flightId = Validator.getFlightIdCancelBooking(bookingId);

        try {
            dao.cancelBooking(bookingId);
            System.out.printf(ConsoleColors.GREEN + "Booking according to booking id " +
                    "number %s have been canceled.\n\n", bookingId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while cancel booking.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }

        return flightId;
    }
}
