package carsharing.dao.car;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CarDAOImpl implements CarDAO {

    private DatabaseManager databaseManager;

    public CarDAOImpl() {
        this.databaseManager = DatabaseManagerImpl.getInstance();
    }

    @Override
    public Map<Integer, String> allCarsByCompany(int id) {
        String sql = "SELECT ID, NAME FROM CAR WHERE COMPANY_ID = (?)";

        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(sql))
        {
            Map<Integer, String> customers = new HashMap<>();
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customers.put(resultSet.getInt("ID"), resultSet.getString("NAME"));
            }
            return customers;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addCarToCompany(Company company, String carName) {
        String sql = "INSERT INTO CAR(NAME, COMPANY_ID) VALUES (?, ?)";

        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(sql))
        {
            ps.setString(1, carName);
            ps.setInt(2, company.id());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
