package carsharing.database.company;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.database.DbCommand;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateCompanyTable implements DbCommand {

    @Override
    public void run() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS `COMPANY` (
                       `ID` integer PRIMARY KEY AUTO_INCREMENT,
                       `NAME` varchar(255) UNIQUE NOT NULL
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
