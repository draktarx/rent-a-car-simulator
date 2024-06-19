package carsharing.ui.menu;

import carsharing.model.Company;
import carsharing.ui.UI;
import carsharing.utils.InputManager;

enum CompanyMenuOption {
    CAR_LIST(1, "Car list"),
    CREATE_A_CAR(2, "Create a car");

    private final int id;

    private final String msg;

    CompanyMenuOption(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public static CompanyMenuOption parse(int id) {
        return switch (id) {
            case 1 -> CAR_LIST;
            case 2 -> CREATE_A_CAR;
            default -> null;
        };
    }
}

public class CompanyMenu extends Menu implements MenuWithOptions {

    private final Company company;

    public CompanyMenu(Company company) {
        this.company = company;
    }

    @Override
    public void show() {
        System.out.printf("'%s' company\n", company.name());
        for (CompanyMenuOption option : CompanyMenuOption.values()) {
            System.out.println(String.format("%d. %s", option.getId(), option.getMsg()));
        }
        System.out.println("0. Back");
        handleOptions();
    }

    @Override
    public void handleOptions() {
        int input = InputManager.readInt();
        if (input == 0) {
            UI.showMenu(new ManagerMenu());
        } else {
            switch (CompanyMenuOption.parse(input)) {
                case CAR_LIST -> UI.showMenu(new CompanyCarsMenu(company));
                case CREATE_A_CAR -> UI.showMenu(new AddCarToCompany(company));
            }

        }
    }

}
