package az.iktlab.dao;

import java.sql.SQLException;
import java.sql.Statement;

public interface JdbcConnection {
    Statement getStatement() throws SQLException;
}
