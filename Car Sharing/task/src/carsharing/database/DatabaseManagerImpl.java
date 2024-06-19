package carsharing.database;

import carsharing.CommandLineArguments;
import carsharing.database.car.CreateCarTable;
import carsharing.database.company.CreateCompanyTable;
import carsharing.database.customer.CreateCustomerTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagerImpl implements DatabaseManager {

    private static final String JDBC_DRIVER = "org.h2.Driver";

    private final Connection connection;

    private static DatabaseManagerImpl instance = null;

    private DatabaseManagerImpl() {
        try {
            Class.forName(JDBC_DRIVER);
            connection =
                    DriverManager.getConnection("jdbc:h2:./src/carsharing/db/" +
                                                CommandLineArguments.getInstance()
                                                                    .getDatabaseName());
            //System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DatabaseManagerImpl getInstance() {
        if (instance == null) {
            instance = new DatabaseManagerImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void createTables() {
        List<DbCommand> commands = new ArrayList<>();
        commands.add(new CreateCompanyTable());
        commands.add(new CreateCarTable());
        commands.add(new CreateCustomerTable());
        commands.forEach(DbCommand::run);
    }

}
