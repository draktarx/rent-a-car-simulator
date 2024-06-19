package carsharing.database.car;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.database.DbCommand;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateCarTable implements DbCommand {

    @Override
    public void run() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS `CAR`
                     (
                         `ID`         integer PRIMARY KEY AUTO_INCREMENT,
                         `NAME`       varchar(255) UNIQUE NOT NULL,
                         `COMPANY_ID` integer             NOT NULL,
                         FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`ID`)
                     );
                     """;

        try (Statement stmt = ((DatabaseManager) DatabaseManagerImpl.getInstance()).getConnection()
                                                                                   .createStatement())
        {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
