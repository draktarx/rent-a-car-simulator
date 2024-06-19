package carsharing.ui.menu;

import carsharing.dao.customer.CustomerDAO;
import carsharing.dao.customer.CustomerDAOImpl;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Map;

public class CustomerLogin extends Menu implements MenuWithInput {

    private final CustomerDAO customerDAO;

    public CustomerLogin() {
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void show() {
        Map<Integer, String> all = customerDAO.list();
        if (all.isEmpty()) {
            System.out.println("The customer list is empty!\n");
            UI.showMenu(new StartMenu());
        } else {
            System.out.println("Choose a customer:");
            for (Map.Entry<Integer, String> entry : all.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            System.out.println("0. Back");
            askUser();
        }
    }

    @Override
    public void askUser() {
        int input = InputManager.readInt();
        Customer customer = customerDAO.find(input);
        if (input == 0) {
            UI.showMenu(new StartMenu());
        } else {
            UI.showMenu(new CustomerMenu(customer));
        }
    }

}
