package carsharing.ui.menu;

import carsharing.dao.customer.CustomerDAO;
import carsharing.dao.customer.CustomerDAOImpl;
import carsharing.model.Car;
import carsharing.model.Customer;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Objects;

enum CustomerMenuOption {
    RENT_A_CAR(1, "Rent a car"),
    RETURN_RENTED_CAR(2, "Return a rented car"),
    RENTED_CAR_INFO(3, "My rented car");

    private final int id;

    private final String msg;

    CustomerMenuOption(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public static CustomerMenuOption parse(int id) {
        return switch (id) {
            case 1 -> RENT_A_CAR;
            case 2 -> RETURN_RENTED_CAR;
            case 3 -> RENTED_CAR_INFO;
            default -> null;
        };
    }
}

public class CustomerMenu extends Menu implements MenuWithOptions {

    private final Customer customer;

    private final CustomerDAO customerDAO;

    public CustomerMenu(Customer customer) {
        this.customer = customer;
        customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void show() {
        for (CustomerMenuOption option : CustomerMenuOption.values()) {
            System.out.printf("%d. %s%n", option.getId(), option.getMsg());
        }
        System.out.println("0. Back");
        handleOptions();
    }

    @Override
    public void handleOptions() {
        int input = InputManager.readInt();
        if (input == 0) {
            UI.showMenu(new CustomerLogin());
        }
        switch (CustomerMenuOption.parse(input)) {
            case RENT_A_CAR -> {
                Car car = customerDAO.rentedCar();
                if (Objects.nonNull(car)) {
                    System.out.println("You've already rented a car!\n");
                    UI.showMenu(new CustomerMenu(customer));
                } else {
                    UI.showMenu(new RentACarMenu(customer));
                }
            }
            case RETURN_RENTED_CAR -> UI.showMenu(new ReturnCarMenu(customer));
            case RENTED_CAR_INFO -> UI.showMenu(new RentedCarInfo(customer));
        }
    }

}
