package carsharing.ui.menu;

import carsharing.dao.company.CompanyDAO;
import carsharing.dao.company.CompanyDAOImpl;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Map;

public class RentACarMenu extends Menu implements MenuWithInput {

    private CompanyDAO companyDAO;
    private Customer customer;

    public RentACarMenu(Customer customer) {
        companyDAO = new CompanyDAOImpl();
        this.customer = customer;
    }

    @Override
    public void show() {
        Map<Integer, String> all = companyDAO.list();
        if (all.isEmpty()) {
            System.out.println("The company list is empty!");
            UI.showMenu(new ManagerMenu());
        } else {
            System.out.println("Choose a company:");
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
        Company company = companyDAO.find(input);
        if (input == 0) {
            UI.showMenu(new CustomerMenu(customer));
        } else {
            UI.showMenu(new ChooseCarToRent(company, customer));
        }
    }

}
