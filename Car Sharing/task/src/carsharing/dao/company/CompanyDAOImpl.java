package carsharing.dao.company;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CompanyDAOImpl implements CompanyDAO {

    private DatabaseManager databaseManager;

    public CompanyDAOImpl() {
        this.databaseManager = DatabaseManagerImpl.getInstance();
    }

    @Override
    public Company find(int id) {
        String newCompanySQL = "SELECT ID, NAME FROM COMPANY WHERE ID = ?";

        try (PreparedStatement ps = databaseManager.getConnection()
                                                   .prepareStatement(newCompanySQL))
        {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int companyId = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                return new Company(companyId, name);
            } else {
                return null; // or you could throw an exception here
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCompany(String name) {
        String newCompanySQL = "INSERT INTO COMPANY (NAME) VALUES (?)";

        try (PreparedStatement ps = databaseManager.getConnection()
                                                   .prepareStatement(newCompanySQL))
        {
            ps.setString(1, name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Map<Integer, String> list() {
        String sql = "SELECT ID, NAME FROM COMPANY;";

        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(sql))
        {
            Map<Integer, String> customers = new HashMap<>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customers.put(resultSet.getInt("ID"), resultSet.getString("NAME"));
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
