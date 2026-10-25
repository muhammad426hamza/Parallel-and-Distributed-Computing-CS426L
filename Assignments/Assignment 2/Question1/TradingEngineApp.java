import java.util.Scanner;

// Menu-driven application demonstrating inheritance and method overriding.
public class TradingEngineApp {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Trading Event Dispatcher ===");
            System.out.println("1. Dispatch a FIX purchase order");
            System.out.println("2. Dispatch a Reuters market quote");
            System.out.println("3. Dispatch a generic event");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option: ", 1, 4);

            if (choice == 1) {
                String sender = readText("Enter sender ID: ");
                String orderId = readText("Enter client order ID: ");
                double value = readPositiveDouble("Enter order value: ");
                // Parent reference calls the overridden child method at runtime.
                TradeEvent event = new FIXOrderEvent(sender, System.currentTimeMillis(), value, orderId);
                event.dispatch();
            } else if (choice == 2) {
                String sender = readText("Enter sender ID: ");
                double bid = readPositiveDouble("Enter bid price: ");
                double ask = readPositiveDouble("Enter ask price: ");
                while (ask < bid) {
                    System.out.println("Invalid quote: ask price must be equal to or higher than bid price.");
                    ask = readPositiveDouble("Enter ask price again: ");
                }
                TradeEvent event = new ReutersQuoteEvent(sender, System.currentTimeMillis(), bid, ask);
                event.dispatch();
            } else if (choice == 3) {
                String sender = readText("Enter sender ID: ");
                double value = readPositiveDouble("Enter base value: ");
                TradeEvent event = new TradeEvent(sender, System.currentTimeMillis(), value);
                event.dispatch();
            } else {
                System.out.println("Trading application closed.");
                break;
            }
        }
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