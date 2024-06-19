package carsharing.ui.menu;

import carsharing.dao.company.CompanyDAO;
import carsharing.dao.company.CompanyDAOImpl;
import carsharing.model.Company;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Map;

public class CompanyListMenu extends Menu implements MenuWithInput {

    private CompanyDAO companyDAO;

    public CompanyListMenu() {
        companyDAO = new CompanyDAOImpl();
    }

    @Override
    public void show() {
        Map<Integer, String> all = companyDAO.list();
        if (all.isEmpty()) {
            System.out.println("The company list is empty!");
            UI.showMenu(new ManagerMenu());
        } else {
            System.out.println("Choose the company:");
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
            UI.showMenu(new ManagerMenu());
        } else {
            UI.showMenu(new CompanyMenu(company));
        }
    }

}
