import java.util.Scanner;

// Menu-driven application demonstrating inheritance and method overriding.
public class TradingEngineApp {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

    }

    // Read an integer and reject letters or values outside the menu range.
    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String line = input.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max)
                    return value;
            } catch (NumberFormatException ignored) {
                // Show the same validation message below.
            }
            System.out.println("Please enter a whole number from " + min + " to " + max + ".");
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            if (!value.isEmpty())
                return value;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            String line = input.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (Double.isFinite(value) && value > 0)
                    return value;
            } catch (NumberFormatException ignored) {
                // Show the same validation message below.
            }
            System.out.println("Please enter a valid number greater than zero.");
        }
    }

}