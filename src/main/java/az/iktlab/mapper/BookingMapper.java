package az.iktlab.mapper;

import az.iktlab.dao.entity.BookingEntity;
import az.iktlab.model.Booking;
import az.iktlab.util.Validator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

    public static List<BookingEntity> mapFromRsToEntity(ResultSet rs) throws SQLException {
        List<BookingEntity> bookings = new ArrayList<>();
        while (rs.next()) {
            bookings.add(new BookingEntity(rs.getLong("booking_id"),
                    rs.getLong("flight_id"),
                    rs.getString("username"),
                    rs.getString("passenger_name"),
                    rs.getString("passenger_surname"),
                    Validator.validateGender(rs.getString("gender"))));
        }
        return bookings;
    }


    public static List<Booking> mapToDto(List<BookingEntity> bookings) {
        return bookings.stream()
                .map(booking -> new Booking(booking.getBookingId(), booking.getFlightId(), booking.getUsername(), booking.getPassengerName(), booking.getPassengerSurname(), booking.getGender()))
                .collect(Collectors.toList());
    }

    public static int mapFromRsToCount(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public static BookingEntity mapToEntity(Booking booking) {
        BookingEntity entity = new BookingEntity();

        entity.setBookingId(booking.getBookingId());
        entity.setFlightId(booking.getFlightId());
        entity.setUsername(booking.getUsername());
        entity.setPassengerName(booking.getPassengerName());
        entity.setPassengerSurname(booking.getPassengerSurname());
        entity.setGender(booking.getGender());

        return entity;
    }
}
