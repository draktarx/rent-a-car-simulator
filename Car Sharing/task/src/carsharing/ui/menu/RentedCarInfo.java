package carsharing.ui.menu;

import carsharing.dao.customer.CustomerDAO;
import carsharing.dao.customer.CustomerDAOImpl;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.ui.UI;

public class RentedCarInfo extends Menu {

    private final Customer customer;

    private final CustomerDAO customerDAO;

    public RentedCarInfo(Customer customer) {
        this.customer = customer;
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void show() {
        Car car = customerDAO.rentedCar();
        if (car == null) {
            System.out.println("You didn't rent a car!\n");
        } else {
            Company company = customerDAO.rentedCarCompany();
            System.out.println("Your rented car:");
            System.out.println(car.name());
            System.out.println("Company:");
            System.out.println(company.name());
            System.out.println();
        }

        UI.showMenu(new CustomerMenu(customer));
    }

}
