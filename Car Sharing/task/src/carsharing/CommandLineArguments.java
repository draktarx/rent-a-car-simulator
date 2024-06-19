package carsharing;

public class CommandLineArguments {

    public static final String DEFAULT_DATABASE_NAME = "carsharing";

    public static final String DB_NAME_ARG = "-databaseFileName";

    private static CommandLineArguments instance;

    private final String databaseName;

    private CommandLineArguments(String[] args) {
        if (args.length > 0 && DB_NAME_ARG.equals(args[0])) {
            databaseName = args[1];
        } else {
            databaseName = DEFAULT_DATABASE_NAME;
        }
    }

    public static CommandLineArguments getInstance(String[] args) {
        if (instance == null) {
            instance = new CommandLineArguments(args);
        }
        return instance;
    }

    public static CommandLineArguments getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance was not initialized previously with arguments");
        }
        return instance;
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
