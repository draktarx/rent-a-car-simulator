package carsharing.database;

import java.sql.Connection;

public interface DatabaseManager {

    Connection getConnection();

    void createTables();
}
