package az.iktlab.util;

import az.iktlab.dao.entity.BookingEntity;
import az.iktlab.dao.entity.FlightEntity;
import az.iktlab.dao.entity.UserEntity;
import az.iktlab.model.Gender;
import az.iktlab.model.User;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class SqlQuery {

    public static String createFlightsTable() {
        return "CREATE TABLE fb_sc.flights(\n" +
                "    flight_id serial PRIMARY KEY,\n" +
                "    destination_from varchar(150) NOT NULL ,\n" +
                "    destination_to varchar(150) NOT NULL ,\n" +
                "    date date NOT NULL,\n" +
                "    time time NOT NULL,\n" +
                "    seats int NOT NULL,\n" +
                "    empty_seats int NOT NULL\n" +
                ");";
    }

    public static String createUsersTable() {
        return "CREATE TABLE fb_sc.users(\n" +
                "    user_id serial PRIMARY KEY,\n" +
                "    username varchar(50) NOT NULL ,\n" +
                "    password varchar(50) NOT NULL ,\n" +
                "    first_name varchar(50) NOT NULL,\n" +
                "    last_name varchar(50) NOT NULL,\n" +
                "    age int NOT NULL,\n" +
                "    gender varchar(8) NOT NULL\n" +
                ");";
    }

    public static String createBookingsTable() {
        return "CREATE TABLE fb_sc.bookings(\n" +
                "    booking_id serial PRIMARY KEY,\n" +
                "    flight_id bigint NOT NULL ,\n" +
                "    username varchar(50) NOT NULL ,\n" +
                "    passenger_name varchar(50) NOT NULL,\n" +
                "    passenger_surname varchar(50) NOT NULL,\n" +
                "    gender varchar(8) NOT NULL\n" +
                ");";
    }

    public static String fillInFlightsTable() {
        return "INSERT INTO fb_sc.flights\n" +
                "   (destination_from,destination_to,date,time,seats,empty_seats)\n" +
                "   VALUES\n" +
                "   ('Baku','Dubai','2022-03-15','17:30',150,150),\n" +
                "   ('Baku','Ankara','2022-03-16','23:30',150,150),\n" +
                "   ('Baku','Kiev','2022-03-15','23:30',150,150),\n" +
                "   ('Baku','Ankara','2022-03-16','17:30',150,150),\n" +
                "   ('Baku','Kiev','2022-03-14','23:59',150,150),\n" +
                "   ('Baku','Ankara','2022-03-17','00:00',150,150),\n" +
                "   ('Baku','Istanbul','2022-03-18','17:30',150,150)";
    }

    public static String showSearchingFlight(FlightEntity flightEntity) {
        return String.format(
                "select * from fb_sc.flights\n" +
                        "   where destination_from='%s'\n" +
                        "   and destination_to='%s' and\n" +
                        "   date='%s' and time = '%s' and empty_seats>=%s",
                flightEntity.getDestinationFrom(),
                flightEntity.getDestinationTO(),
                flightEntity.getDate(),
                flightEntity.getTime(),
                flightEntity.getEmptySeats());
    }

    public static String getAllNext24Flights(LocalDate date, LocalDate nextDate) {
        return String.format("Select * from fb_sc.flights\n" +
                "   where date between '%s' and '%s'", date, nextDate);
    }

    public static String getMyBookings(String username) {
        return String.format("select * from fb_sc.bookings\n" +
                "   where username = '%s'", username);
    }

    public static String showInfoFlight(Long flightId) {
        return String.format(
                "select * from fb_sc.flights\n" +
                        "   where flight_id=%s", flightId);
    }

    public static String bookingFlight(BookingEntity bookingEntity) {
        return String.format(
                "insert into fb_sc.bookings\n" +
                        "   (flight_id, username, passenger_name, passenger_surname,gender)\n" +
                        "   values (%s,'%s','%s','%s','%s')",
                bookingEntity.getFlightId(),
                bookingEntity.getUsername(),
                bookingEntity.getPassengerName(),
                bookingEntity.getPassengerSurname(),
                bookingEntity.getGender());
    }

    public static String emptySeatsDecrease(long flightId) {
        return String.format("update fb_sc.flights set empty_seats = empty_seats-1\n" +
                "   where flight_id = %s", flightId);
    }

    public static String emptySeatsIncrease(long flightId) {
        return String.format("update fb_sc.flights set empty_seats = empty_seats+1\n" +
                "   where flight_id = %s", flightId);
    }

    public static String checkLogin(String username, String password) {
        return String.format("select count(username) from fb_sc.users where\n" +
                "   username='%s' and password ='%s'", username, password);
    }

    public static String countEmptySeats(long flightId) {
        return String.format("select count(empty_seats) from fb_sc.flights where\n" +
                "   flight_id = '%s' and empty_seats>0", flightId);
    }

    public static String checkUsername(String username) {
        return String.format("select count(username) from fb_sc.users where\n" +
                "   username='%s'", username);
    }

    public static String saveUser(UserEntity userEntity) {
        return String.format("INSERT INTO fb_sc.users\n" +
                        "   (username, password,first_name, last_name, age, gender)\n" +
                        "   VALUES ('%s', '%s', '%s','%s', %s, '%s')",
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAge(),
                userEntity.getGender());
    }

    public static String checkMyBookings(String username) {
        return String.format("select count(username) from fb_sc.bookings where\n" +
                "   username='%s'", username);
    }


    public static String checkPassengerBookingTable(BookingEntity bookingEntity) {
        return String.format("select count(booking_id) from fb_sc.bookings where\n" +
                        "   flight_id='%s' and username= '%s'\n" +
                        "   and passenger_name = '%s' and passenger_surname = '%s'\n" +
                        "   and gender = '%s'",
                bookingEntity.getFlightId(),
                bookingEntity.getUsername(),
                bookingEntity.getPassengerName(),
                bookingEntity.getPassengerSurname(),
                bookingEntity.getGender());
    }

    public static String checkBookingIdInDatabase(long bookingId) {
        return String.format("select count(booking_id) from fb_sc.bookings where\n" +
                "   booking_id='%s'", bookingId);
    }

    public static String getFlightIdCancelBooking(long bookingId) {
        return String.format("select * from fb_sc.bookings where\n" +
                "   booking_id='%s'", bookingId);
    }

    public static String cancelBooking(long bookingId) {
        return String.format("DELETE FROM fb_sc.bookings WHERE\n" +
                "   booking_id=%s", bookingId);
    }

    public static String checkFlightId(long flightId) {
        return String.format("select count(flight_id) from fb_sc.flights where\n" +
                "   flight_id='%s'", flightId);
    }

    public static String getCountSearchResult(FlightEntity flightEntity) {
        return String.format("select count(flight_id) from fb_sc.flights where\n" +
                        "   destination_from = '%s'\n" +
                        "   and destination_to = '%s'\n" +
                        "   and date = '%s'\n" +
                        "   and time = '%s'\n" +
                        "   and empty_seats >= '%s'\n",
                flightEntity.getDestinationFrom(),
                flightEntity.getDestinationTO(),
                flightEntity.getDate(),
                flightEntity.getTime(),
                flightEntity.getEmptySeats());
    }

}
