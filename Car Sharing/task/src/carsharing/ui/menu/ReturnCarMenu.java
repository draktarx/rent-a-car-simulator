package carsharing.ui.menu;

import carsharing.dao.customer.CustomerDAO;
import carsharing.dao.customer.CustomerDAOImpl;
import carsharing.model.Customer;
import carsharing.ui.UI;

public class ReturnCarMenu extends Menu {

    private final CustomerDAO customerDAO;
    private final Customer customer;

    public ReturnCarMenu(Customer customer) {
        this.customer = customer;
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void show() {
        boolean isCarReturned = customerDAO.returnCar(customer.id());
        if (isCarReturned) {
            System.out.println("You've returned a rented car!\n");
        } else {
            System.out.println("You didn't rent a car!\n");
        }

        UI.showMenu(new CustomerMenu(customer));
    }

}
