import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

// Compares a synchronized HashMap with ConcurrentHashMap using the same workload.
public class InventoryCatalogManager {
    private static final Scanner input = new Scanner(System.in);
    private static final int WORKER_COUNT = 10;
    private static final int TOTAL_OPERATIONS = 50000;

    public static void main(String[] args) {
        
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                if (value >= min && value <= max)
                    return value;
            } catch (NumberFormatException ignored) {
                // Handle invalid input below.
            }
            System.out.println("Please enter a whole number from " + min + " to " + max + ".");
        }
    }

    private static void printResult(String name, long elapsedNanos, long finalStock, int productCount) {
        double seconds = elapsedNanos / 1_000_000_000.0;
        double throughput = seconds > 0 ? TOTAL_OPERATIONS / seconds : 0.0;
        long expectedTotal = (long) productCount * 100 + TOTAL_OPERATIONS;
        System.out.println("\nCollection: " + name);
        System.out.println("Worker threads: " + WORKER_COUNT);
        System.out.println("Lookup/update operations: " + TOTAL_OPERATIONS);
        System.out.printf("Execution time: %.3f ms%n", elapsedNanos / 1_000_000.0);
        System.out.printf("Throughput: %.0f operations/second%n", throughput);
        System.out.println("Final stock total: " + finalStock + " (expected " + expectedTotal + ")");
    }

    private static long totalStock(Map<String, Integer> catalog) {
        long total = 0;
        // Iterating synchronizedMap requires a synchronized block.
        synchronized (catalog) {
            for (Integer stock : catalog.values()) total += stock;
        }
        return total;
    }
}