package carsharing.ui.menu;

import carsharing.dao.company.CompanyDAO;
import carsharing.dao.company.CompanyDAOImpl;
import carsharing.model.Company;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Objects;

public class CreateCompanyMenu extends Menu {

    private CompanyDAO companyDAO;

    public CreateCompanyMenu() {
        this.companyDAO = new CompanyDAOImpl();
    }

    @Override
    public void show() {
        String name = null;
        do {
            System.out.println("Enter the company name:");
            name = InputManager.readString();
        } while (Objects.isNull(name) || name.isEmpty());
        boolean success = companyDAO.addCompany(name);
        if (success) {
            System.out.println("The company was created!.\n");
        }
        UI.showMenu(new ManagerMenu());
    }

}
