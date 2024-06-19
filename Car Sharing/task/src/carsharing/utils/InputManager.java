package carsharing.utils;

import java.util.Scanner;

public class InputManager {

    private static final Scanner scanner = new Scanner(System.in);

        private InputManager() {
            // Disallow instantiation
        }

        public static String readString() {
            String input = scanner.nextLine();
            //System.out.println();
            return input;
        }

        public static int readInt() {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid integer. Try again.");
                scanner.next();
            }
            int number = scanner.nextInt();
            // Consume newline left-over
            scanner.nextLine();
            System.out.println();
            return number;
        }
}

