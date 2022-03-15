package az.iktlab.service;

import az.iktlab.dao.entity.FlightEntity;
import az.iktlab.dao.repo.FlightDao;
import az.iktlab.mapper.FlightMapper;
import az.iktlab.model.Flight;
import az.iktlab.util.ConsoleColors;
import az.iktlab.util.Validator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightService {

    private final FlightDao dao;


    List<FlightEntity> flights = new ArrayList<>();

    public FlightService(FlightDao dao) {
        this.dao = dao;
    }

    public List<Flight> searchFlight(Flight flight) {

        if (Validator.getCountSearchResult(flight) == false)
            return new ArrayList<>();

        FlightEntity flightEntity = FlightMapper.mapToEntity(flight);

        try {
            flights = dao.showSearchingFlight(flightEntity);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "Error occurred when searching flights.");
            System.out.printf("Error message: %s", e.getMessage() + "\n");
        }
        return FlightMapper.mapToDto(flights);
    }

    public List<Flight> getAllNext24Flights(LocalDate date, LocalDate nextDate) {
        List<FlightEntity> flights = new ArrayList<>();

        try {
            flights = dao.getAllNext24Flights(date, nextDate);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "Error occurred when searching flights.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }

        return FlightMapper.mapToDto(flights);

    }

    public List<Flight> showInfoFlight(Long flightId) {
        List<FlightEntity> flights = new ArrayList<>();
        try {
            flights = dao.showInfoFlight(flightId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "Error occurred while showing flight info.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }

        return FlightMapper.mapToDto(flights);
    }

    public void emptySeatsDecrease(long flightId) {
        try {
            dao.emptySeatsDecrease(flightId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while booking flight.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
    }

    public void emptySeatsIncrease(long flightId) {
        try {
            dao.emptySeatsIncrease(flightId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "Error occurred while booking flight.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
    }

    public long checkFlightIdInDatabase(long flightId) {
        int count = 0;
        try {
            count = dao.checkFlightIdInDatabase(flightId);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "Error occurred while checking flight id.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
        if (count > 0) {
            return flightId;
        } else {
            return 0;
        }
    }

    public int checkEmptySeats(long flightID) {
        int count = 0;
        try {
            count = dao.countEmptySeats(flightID);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "Error occurred while checking empty seats.");
            System.out.printf(ConsoleColors.RED + "Error message: %s", e.getMessage() + "\n");
        }
        return count;
    }

    public int getCountSearchResult(Flight flight) {
        if (Validator.checkCityFirstLetter(flight.getDestinationFrom()) == false) return -1;
        if (Validator.checkCityFirstLetter(flight.getDestinationTO()) == false) return -1;

        FlightEntity flightEntity = FlightMapper.mapToEntity(flight);

        int count = 0;
        try {
            count = dao.getCountSearchResult(flightEntity);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "An error occurred while get search count flight.");
            System.out.println(ConsoleColors.RED + "Error message: %s" + e.getMessage() + "\n");
        }
        return count;
    }
}
