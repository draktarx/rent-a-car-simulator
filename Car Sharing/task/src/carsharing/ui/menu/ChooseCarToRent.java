package carsharing.ui.menu;

import carsharing.dao.car.CarDAO;
import carsharing.dao.car.CarDAOImpl;
import carsharing.dao.customer.CustomerDAO;
import carsharing.dao.customer.CustomerDAOImpl;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Map;
import java.util.Objects;

public class ChooseCarToRent extends Menu implements MenuWithInput{

    private final Company company;
    private final Customer customer;

    private final CarDAO carDAO;
    private final CustomerDAO customerDAO;

    public ChooseCarToRent(Company company, Customer customer) {
        this.company = company;
        this.customer = customer;
        this.carDAO = new CarDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void show() {
        System.out.println("Choose a car:");
        Map<Integer, String> all = carDAO.allCarsByCompany(company.id());
        if (Objects.isNull(all) || all.isEmpty()) {
            System.out.println("The car list is empty!\n");
        } else {
            //System.out.println(String.format("%s cars:", company.name()));
            for (Map.Entry<Integer, String> entry : all.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            System.out.println("0. Back");
            askUser();
        }
    }

    @Override
    public void askUser() {
        int carId = InputManager.readInt();
        Car car = customerDAO.rentCar(carId, customer.id());
        if (Objects.nonNull(car)) {
            System.out.println(String.format("You rented '%s'\n", car.name()));
        }
        UI.showMenu(new CustomerMenu(customer));
    }

}
