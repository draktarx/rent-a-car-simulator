package carsharing.dao.customer;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;

import java.util.Map;

public interface CustomerDAO {

    boolean save(String customer);

    Map<Integer, String> list();

    Customer find(int id);

    Car rentedCar();

    Company rentedCarCompany();

    boolean returnCar(int customerId);

    Car rentCar(int carId, int customerId);

}
