package carsharing.ui.menu;

import carsharing.ui.UI;
import carsharing.utils.InputManager;

enum StartMenuOption {
    LOGIN_AS_MANAGER(1, "Log in as a manager"),
    LOGIN_AS_CUSTOMER(2, "Log in as a customer"),
    CREATE_A_CUSTOMER(3, "Create a customer");

    private final int id;

    private final String msg;

    StartMenuOption(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public static StartMenuOption parse(int id) {
        return switch (id) {
            case 1 -> LOGIN_AS_MANAGER;
            case 2 -> LOGIN_AS_CUSTOMER;
            case 3 -> CREATE_A_CUSTOMER;
            default -> null;
        };
    }
}

public class StartMenu extends Menu implements MenuWithOptions {

    private MenuWithInput createCustomer;

    public StartMenu() {
        this.createCustomer = new CreateCustomer();
    }

    @Override
    public void show() {
        for (StartMenuOption option : StartMenuOption.values()) {
            System.out.printf("%d. %s%n", option.getId(), option.getMsg());
        }
        System.out.println("0. Exit");
        handleOptions();
        System.out.println();
    }

    @Override
    public void handleOptions() {
        int input = InputManager.readInt();
        if (input == 0) {
            System.exit(0);
        }
        switch (StartMenuOption.parse(input)) {
            case LOGIN_AS_MANAGER -> UI.showMenu(new ManagerMenu());
            case LOGIN_AS_CUSTOMER -> UI.showMenu(new CustomerLogin());
            case CREATE_A_CUSTOMER -> UI.showMenu(new CreateCustomer());
            default -> throw new IllegalStateException("Unexpected value: " +
                                                       StartMenuOption.parse(InputManager.readInt()));
        }
    }

}
