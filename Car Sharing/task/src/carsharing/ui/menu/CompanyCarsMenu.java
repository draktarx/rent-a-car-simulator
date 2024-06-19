package carsharing.ui.menu;

import carsharing.dao.car.CarDAO;
import carsharing.dao.car.CarDAOImpl;
import carsharing.model.Company;
import carsharing.ui.UI;

import java.util.Map;
import java.util.Objects;

public class CompanyCarsMenu extends Menu {

    private final Company company;

    private final CarDAO carDAO;

    public CompanyCarsMenu(Company company) {
        this.company = company;
        this.carDAO = new CarDAOImpl();
    }

    @Override
    public void show() {
        Map<Integer, String> all = carDAO.allCarsByCompany(company.id());
        if (Objects.isNull(all) || all.isEmpty()) {
            System.out.println("The car list is empty!\n");
        } else {
            System.out.println("Car list:");
            int index = 1;
            for (Map.Entry<Integer, String> entry : all.entrySet()) {
                System.out.println(index + ". " + entry.getValue());
                index++;
            }
            System.out.println();
        }

        UI.showMenu(new CompanyMenu(company));
    }

}
