package az.iktlab.dao.entity;

import az.iktlab.model.Gender;

import java.util.Objects;

public class BookingEntity {
    private long bookingId;
    private long flightId;
    private String username;
    private String passengerName;
    private String passengerSurname;
    private Gender gender;

    public BookingEntity() {
    }


    public BookingEntity(long flightId, String username, String passengerName, String passengerSurname,
                         Gender gender) {
        this.flightId = flightId;
        this.username = username;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.gender = gender;
    }

    public BookingEntity(long bookingId, long flightId, String username,
                         String passengerName, String passengerSurname, Gender gender) {
        this.bookingId = bookingId;
        this.flightId = flightId;
        this.username = username;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.gender = gender;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return bookingId == that.bookingId && flightId == that.flightId && Objects.equals(username, that.username) && Objects.equals(passengerName, that.passengerName) && Objects.equals(passengerSurname, that.passengerSurname) && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, flightId, username, passengerName, passengerSurname, gender);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "bookingId=" + bookingId +
                ", flightId=" + flightId +
                ", username='" + username + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", passengerSurname='" + passengerSurname + '\'' +
                ", gender=" + gender +
                '}';
    }
}
