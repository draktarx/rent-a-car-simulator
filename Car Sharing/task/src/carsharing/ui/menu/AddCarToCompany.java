package carsharing.ui.menu;

import carsharing.dao.car.CarDAO;
import carsharing.dao.car.CarDAOImpl;
import carsharing.model.Company;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

import java.util.Objects;

public class AddCarToCompany extends Menu implements MenuWithInput {

    private final Company company;

    private final CarDAO carDAO;

    public AddCarToCompany(Company company) {
        this.company = company;
        this.carDAO = new CarDAOImpl();
    }

    @Override
    public void show() {
        askUser();
    }

    @Override
    public void askUser() {
        String carName = null;
        do {
            System.out.println("Enter the car name:");
            carName = InputManager.readString();
        } while (Objects.isNull(carName) || carName.isEmpty());
        boolean success = carDAO.addCarToCompany(company, carName);
        if (success) {
            System.out.println("The car was added!\n");
        }
        UI.showMenu(new CompanyMenu(company));
    }

}
