package az.iktlab.dao.repo;

import az.iktlab.dao.JdbcConnection;
import az.iktlab.dao.PgSql;
import az.iktlab.dao.entity.BookingEntity;
import az.iktlab.mapper.BookingMapper;
import az.iktlab.util.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookingDao {
    JdbcConnection jdbcConnection = new PgSql();

    public int checkMyBookings(String username) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.checkMyBookings(username));
        return BookingMapper.mapFromRsToCount(rs);
    }

    public List<BookingEntity> showMyBookings(String username) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.getMyBookings(username));
        return BookingMapper.mapFromRsToEntity(rs);
    }

    public void bookingFlight(BookingEntity bookingEntity)
            throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        stmt.execute(SqlQuery.bookingFlight(bookingEntity));
    }

    public int checkPassengerBookingTable(BookingEntity bookingEntity)
            throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.checkPassengerBookingTable(bookingEntity));
        return BookingMapper.mapFromRsToCount(rs);
    }

    public int checkBookingIdInDatabase(long bookingId) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.checkBookingIdInDatabase(bookingId));
        return BookingMapper.mapFromRsToCount(rs);
    }

    public List<BookingEntity> getFlightIdCancelBooking(long bookingId)
            throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.getFlightIdCancelBooking(bookingId));
        return BookingMapper.mapFromRsToEntity(rs);
    }

    public void cancelBooking(long bookingId) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        stmt.execute(SqlQuery.cancelBooking(bookingId));
    }
}
