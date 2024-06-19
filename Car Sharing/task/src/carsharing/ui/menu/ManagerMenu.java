package carsharing.ui.menu;

import carsharing.ui.UI;
import carsharing.utils.InputManager;

enum ManagerMenuOption {
    LIST_COMPANIES(1, "Company list"),
    CREATE_A_COMPANY(2, "Create a company");

    private final int id;

    private final String msg;

    ManagerMenuOption(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public static ManagerMenuOption parse(int id) {
        return switch (id) {
            case 1 -> LIST_COMPANIES;
            case 2 -> CREATE_A_COMPANY;
            default -> null;
        };
    }
}

public class ManagerMenu extends Menu implements MenuWithOptions {

    @Override
    public void show() {
        for (ManagerMenuOption option : ManagerMenuOption.values()) {
            System.out.printf("%d. %s%n", option.getId(), option.getMsg());
        }
        System.out.println("0. Back");
        handleOptions();
        System.out.println();
    }

    @Override
    public void handleOptions() {
        int input = InputManager.readInt();
        if (input == 0) {
            UI.showMenu(new StartMenu());
        }
        switch (ManagerMenuOption.parse(input)) {
            case LIST_COMPANIES -> UI.showMenu(new CompanyListMenu());
            case CREATE_A_COMPANY -> UI.showMenu(new CreateCompanyMenu());
        }
    }

}
