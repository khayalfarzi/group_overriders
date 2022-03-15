package az.iktlab.mapper;

import az.iktlab.dao.entity.FlightEntity;
import az.iktlab.model.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightMapper {

    public static List<FlightEntity> mapFromRsToEntity(ResultSet rs) throws SQLException {
        List<FlightEntity> flights = new ArrayList<>();
        while (rs.next()) {
            flights.add(new FlightEntity(rs.getLong("flight_id"),
                    rs.getString("destination_from"),
                    rs.getString("destination_to"),
                    rs.getDate("date"),
                    rs.getTime("time"),
                    rs.getInt("seats"),
                    rs.getInt("empty_seats")));
        }
        return flights;
    }

    public static List<Flight> mapToDto(List<FlightEntity> flights) {
        return flights.stream()
                .map(flight -> new Flight(flight.getFlightId(), flight.getDestinationFrom(), flight.getDestinationTO(), flight.getDate(), flight.getTime(), flight.getSeats(), flight.getEmptySeats()))
                .collect(Collectors.toList());
    }

    public static int mapFromRsToCount(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public static FlightEntity mapToEntity(Flight flight) {
        FlightEntity flightEntity = new FlightEntity();

        flightEntity.setFlightId(flight.getFlightId());
        flightEntity.setDestinationFrom(flight.getDestinationFrom());
        flightEntity.setDestinationTO(flight.getDestinationTO());
        flightEntity.setDate(flight.getDate());
        flightEntity.setTime(flight.getTime());
        flightEntity.setSeats(flight.getSeats());
        flightEntity.setEmptySeats(flight.getEmptySeats());

        return flightEntity;
    }
}
