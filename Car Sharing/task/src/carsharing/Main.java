package carsharing;

import carsharing.database.DatabaseManager;
import carsharing.database.DatabaseManagerImpl;
import carsharing.ui.menu.StartMenu;
import carsharing.ui.UI;

public class Main {

    public static void main(String[] args) {
        CommandLineArguments.getInstance(args);
        DatabaseManager databaseManager = DatabaseManagerImpl.getInstance();
        databaseManager.createTables();
        UI.showMenu(new StartMenu());
    }

}

