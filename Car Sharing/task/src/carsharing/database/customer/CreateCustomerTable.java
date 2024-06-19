package carsharing.database.customer;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.database.DbCommand;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateCustomerTable implements DbCommand {

    @Override
    public void run() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS `CUSTOMER`
                     (
                         `ID`            integer PRIMARY KEY AUTO_INCREMENT,
                         `NAME`          varchar(255) UNIQUE NOT NULL,
                         `RENTED_CAR_ID` integer DEFAULT null,
                         FOREIGN KEY (`RENTED_CAR_ID`) REFERENCES `CAR` (`ID`)
                     );
                     """;

        try (Statement stmt = ((DatabaseManager) DatabaseManagerImpl.getInstance()).getConnection()
                                                                                   .createStatement())
        {
            boolean b = stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
