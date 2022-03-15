package az.iktlab.dao.repo;

import az.iktlab.dao.JdbcConnection;
import az.iktlab.dao.PgSql;
import az.iktlab.dao.entity.UserEntity;
import az.iktlab.mapper.UserMapper;
import az.iktlab.util.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    JdbcConnection jdbcConnection = new PgSql();

    public int loginUser(String username, String password) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.checkLogin(username, password));
        return UserMapper.mapFromRsToCount(rs);
    }

    public int checkUsernameInDatabase(String username) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        ResultSet rs = stmt.executeQuery(SqlQuery.checkUsername(username));
        return UserMapper.mapFromRsToCount(rs);
    }

    public boolean registrationUser(UserEntity userEntity) throws SQLException {
        Statement stmt = jdbcConnection.getStatement();
        boolean flag = stmt.execute(SqlQuery.saveUser(userEntity));
        return !flag;
    }
}
