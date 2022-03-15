package az.iktlab.dao.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class FlightEntity {
    private long flightId;
    private String destinationFrom;
    private String destinationTO;
    private Date date;
    private Time time;
    private int seats;
    private int emptySeats;

    public FlightEntity() {
    }

    public FlightEntity(long flightId) {
        this.flightId = flightId;
    }

    public FlightEntity(String destinationFrom, String destinationTO,
                        Date date, Time time, int emptySeats) {
        this.destinationFrom = destinationFrom;
        this.destinationTO = destinationTO;
        this.date = date;
        this.time = time;
        this.emptySeats = emptySeats;
    }

    public FlightEntity(long flightId, String destinationFrom, String destinationTO,
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
        FlightEntity that = (FlightEntity) o;
        return flightId == that.flightId && seats == that.seats && emptySeats == that.emptySeats && Objects.equals(destinationFrom, that.destinationFrom) && Objects.equals(destinationTO, that.destinationTO) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, destinationFrom, destinationTO, date, time, seats, emptySeats);
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
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