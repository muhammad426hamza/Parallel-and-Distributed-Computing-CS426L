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
        while (true) {
            System.out.println("\n=== Inventory Catalog Benchmark ===");
            System.out.println("1. Run both collection benchmarks");
            System.out.println("2. Run synchronized-map benchmark only");
            System.out.println("3. Run ConcurrentHashMap benchmark only");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option: ", 1, 4);
            if (choice == 4) {
                System.out.println("Inventory benchmark closed.");
                break;
            }

            int productCount = readInt("Enter product count (10-1000): ", 10, 1000);
            System.out.println("Each selected test uses " + WORKER_COUNT + " workers and "
                    + TOTAL_OPERATIONS + " lookup/update operations.");
            if (choice == 1 || choice == 2) benchmarkSynchronizedMap(productCount);
            if (choice == 1 || choice == 3) benchmarkConcurrentMap(productCount);
        }
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

    private static void joinWorkers(Thread[] workers) {
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread was interrupted while waiting.");
                return;
            }
        }
    }

    private static void initializeCatalog(Map<String, Integer> catalog, int productCount) {
        for (int i = 0; i < productCount; i++) catalog.put("P" + i, 100);
    }

    private static String productIdFor(int operation, int workerId, int productCount) {
        return "P" + ((operation + workerId) % productCount);
    }

    public static void benchmarkSynchronizedMap(int productCount) {
        Map<String, Integer> catalog = Collections.synchronizedMap(new HashMap<String, Integer>());
        initializeCatalog(catalog, productCount);
        Thread[] workers = new Thread[WORKER_COUNT];
        long startTime = System.nanoTime();

        for (int workerId = 0; workerId < WORKER_COUNT; workerId++) {
            final int id = workerId;
            workers[workerId] = new Thread(() -> {
                int operationsForWorker = TOTAL_OPERATIONS / WORKER_COUNT;
                for (int i = 0; i < operationsForWorker; i++) {
                    String productId = productIdFor(i, id, productCount);
                    // Synchronize the full read-update-write operation to avoid lost updates.
                    synchronized (catalog) {
                        Integer currentStock = catalog.get(productId);
                        catalog.put(productId, currentStock + 1);
                    }
                }
            }, "Sync-Worker-" + (workerId + 1));
            workers[workerId].start();
        }

        joinWorkers(workers);
        long elapsed = System.nanoTime() - startTime;
        printResult("Collections.synchronizedMap", elapsed, totalStock(catalog), productCount);
    }

    public static void benchmarkConcurrentMap(int productCount) {
        ConcurrentHashMap<String, Integer> catalog = new ConcurrentHashMap<String, Integer>();
        initializeCatalog(catalog, productCount);
        Thread[] workers = new Thread[WORKER_COUNT];
        long startTime = System.nanoTime();

        for (int workerId = 0; workerId < WORKER_COUNT; workerId++) {
            final int id = workerId;
            workers[workerId] = new Thread(() -> {
                int operationsForWorker = TOTAL_OPERATIONS / WORKER_COUNT;
                for (int i = 0; i < operationsForWorker; i++) {
                    String productId = productIdFor(i, id, productCount);
                    // compute() updates a key safely without locking the whole map ourselves.
                    catalog.compute(productId, (key, currentStock) ->
                            currentStock == null ? 1 : currentStock + 1);
                }
            }, "Concurrent-Worker-" + (workerId + 1));
            workers[workerId].start();
        }

        joinWorkers(workers);
        long elapsed = System.nanoTime() - startTime;
        printResult("ConcurrentHashMap", elapsed, totalStock(catalog), productCount);
    }
}