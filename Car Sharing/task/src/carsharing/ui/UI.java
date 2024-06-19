package carsharing.ui;

import carsharing.ui.menu.Menu;

public class UI {

    private static Menu currentMenuListable;

    public static void showMenu(Menu next) {
        currentMenuListable = next;
        currentMenuListable.show();
    }

}
