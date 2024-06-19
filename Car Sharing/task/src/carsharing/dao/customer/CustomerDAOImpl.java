package carsharing.dao.customer;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

    private DatabaseManager databaseManager;

    public CustomerDAOImpl() {
        this.databaseManager = DatabaseManagerImpl.getInstance();
    }

    @Override
    public boolean save(String customer) {
        String newCompanySQL = "INSERT INTO CUSTOMER (NAME) VALUES (?)";

        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(newCompanySQL))
        {
            ps.setString(1, customer);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Customer find(int id) {
        String sql = "SELECT ID, NAME FROM CUSTOMER WHERE ID = (?)";

        try (PreparedStatement ps = databaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                return new Customer(customerId, name);
            } else {
                return null; // or you could throw an exception here
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Car rentedCar() {
        String
                sql =
                "SELECT CAR.id, CAR.name FROM CAR JOIN CUSTOMER ON CAR.id = CUSTOMER.RENTED_CAR_ID";

        try (PreparedStatement ps = databaseManager.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int carId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Car(carId, name);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Company rentedCarCompany() {
        String
                sql =
                "SELECT COMPANY.id, COMPANY.name FROM COMPANY JOIN CAR ON COMPANY.id = CAR.COMPANY_ID JOIN CUSTOMER ON CAR.id = CUSTOMER.RENTED_CAR_ID";

        try (PreparedStatement ps = databaseManager.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int companyId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Company(companyId, name);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean returnCar(int customerId) {

        String inspectSql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = ?";
        ResultSet rs;
        try (PreparedStatement inspectPs = DatabaseManagerImpl.getInstance()
                                                              .getConnection()
                                                              .prepareStatement(inspectSql))
        {
            inspectPs.setInt(1, customerId);

            rs = inspectPs.executeQuery();
            if (rs.next()) {
                if (rs.getObject(1) == null) {
                    return false; // Return false if RENTED_CAR_ID is already NULL
                }
            } else {
                return false; // Return false if the customer ID does not exist
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = ?";

        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(sql))
        {
            ps.setInt(1, customerId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Car rentCar(int carId, int customerId) {
        String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = ? WHERE ID = ?";
        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(sql))
        {
            ps.setInt(1, carId);
            ps.setInt(2, customerId);
            ps.executeUpdate();
            return rentedCar();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Map<Integer, String> list() {
        String newCompanySQL = "SELECT ID, NAME FROM CUSTOMER";

        try (PreparedStatement ps = DatabaseManagerImpl.getInstance()
                                                       .getConnection()
                                                       .prepareStatement(newCompanySQL))
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
