package az.iktlab.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Flight {
    private long flightId;
    private String destinationFrom;
    private String destinationTO;
    private Date date;
    private Time time;
    private int seats;
    private int emptySeats;

    public Flight() {
    }

    public Flight(long flightId) {
        this.flightId = flightId;
    }

    public Flight(String destinationFrom, String destinationTO,
                  Date date, Time time, int emptySeats) {
        this.destinationFrom = destinationFrom;
        this.destinationTO = destinationTO;
        this.date = date;
        this.time = time;
        this.emptySeats = emptySeats;
    }

    public Flight(long flightId, String destinationFrom, String destinationTO,
                  Date date, Time time, int seats, int emptySeats) {
        this.flightId = flightId;
        this.destinationFrom = destinationFrom;
        this.destinationTO = destinationTO;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.emptySeats = emptySeats;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(String destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public String getDestinationTO() {
        return destinationTO;
    }

    public void setDestinationTO(String destinationTO) {
        this.destinationTO = destinationTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(int emptySeats) {
        this.emptySeats = emptySeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightId == flight.flightId && seats == flight.seats && emptySeats == flight.emptySeats && Objects.equals(destinationFrom, flight.destinationFrom) && Objects.equals(destinationTO, flight.destinationTO) && Objects.equals(date, flight.date) && Objects.equals(time, flight.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, destinationFrom, destinationTO, date, time, seats, emptySeats);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", destinationFrom='" + destinationFrom + '\'' +
                ", destinationTO='" + destinationTO + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", seats=" + seats +
                ", emptySeats=" + emptySeats +
                '}';
    }
}