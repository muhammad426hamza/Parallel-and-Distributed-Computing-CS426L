import java.util.Random;
import java.util.Scanner;

// Generates 50,000 readings and searches four segments using four worker threads.
public class SensorGridMonitorApp {
    private static final Scanner input = new Scanner(System.in);
    private static final int DATA_SIZE = 50000;
    private static final int WORKER_COUNT = 4;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Parallel Sensor Grid Monitor ===");
            System.out.println("1. Generate readings and find global minimum/maximum");
            System.out.println("2. Exit");
            int choice = readInt("Choose an option: ", 1, 2);
            if (choice == 2) {
                System.out.println("Sensor monitor closed.");
                break;
            }

            double low = readDouble("Enter the lowest possible reading: ");
            double high = readDouble("Enter the highest possible reading: ");
            while (high <= low) {
                System.out.println("Highest reading must be greater than the lowest reading.");
                high = readDouble("Enter the highest possible reading again: ");
            }

            double[] data = createSensorData(low, high);
            SensorRangeWorker[] workers = new SensorRangeWorker[WORKER_COUNT];
            long startTime = System.nanoTime();

            for (int i = 0; i < WORKER_COUNT; i++) {
                int start = i * DATA_SIZE / WORKER_COUNT;
                int end = (i + 1) * DATA_SIZE / WORKER_COUNT;
                ParallelMinSearch minSearch = new ParallelMinSearch(data, start, end);
                ParallelMaxSearch maxSearch = new ParallelMaxSearch(data, start, end);
                workers[i] = new SensorRangeWorker("Sensor-Worker-" + (i + 1), minSearch, maxSearch);
                workers[i].start();
            }

            double globalMin = Double.POSITIVE_INFINITY;
            double globalMax = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < WORKER_COUNT; i++) {
                try {
                    workers[i].join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Main thread was interrupted while waiting.");
                    return;
                }
                if (workers[i].getMinimum() < globalMin)
                    globalMin = workers[i].getMinimum();
                if (workers[i].getMaximum() > globalMax)
                    globalMax = workers[i].getMaximum();
            }

            long elapsedNanos = System.nanoTime() - startTime;
            System.out.println("\nReadings processed: " + DATA_SIZE);
            System.out.printf("Global minimum: %.4f%n", globalMin);
            System.out.printf("Global maximum: %.4f%n", globalMax);
            System.out.printf("Execution time with %d worker threads: %.3f ms%n",
                    WORKER_COUNT, elapsedNanos / 1_000_000.0);
        }
    }

    private static double[] createSensorData(double low, double high) {
        double[] data = new double[DATA_SIZE];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = low + random.nextDouble() * (high - low);
        }
        return data;
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(input.nextLine().trim());
                if (value >= min && value <= max)
                    return value;
            } catch (NumberFormatException ignored) {
                // Handle invalid menu input below.
            }
            System.out.println("Please enter a whole number from " + min + " to " + max + ".");
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(input.nextLine().trim());
                if (Double.isFinite(value))
                    return value;
            } catch (NumberFormatException ignored) {
                // Handle invalid values below.
            }
            System.out.println("Please enter a valid number.");
        }
    }
}