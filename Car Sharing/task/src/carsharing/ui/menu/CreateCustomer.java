package carsharing.ui.menu;

import carsharing.dao.customer.CustomerDAO;
import carsharing.dao.customer.CustomerDAOImpl;
import carsharing.model.Customer;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

public class CreateCustomer extends Menu implements MenuWithInput {

    private final CustomerDAO customerDAO;

    public CreateCustomer() {
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void show() {
        System.out.println("Enter the customer name:");
        askUser();
    }

    @Override
    public void askUser() {
        String name = InputManager.readString();
        boolean saved = customerDAO.save(name);
        if (saved) {
            System.out.println("The customer was added!\n");
        } else {
            System.out.println("The customer exist");
        }
        UI.showMenu(new StartMenu());
    }

}
