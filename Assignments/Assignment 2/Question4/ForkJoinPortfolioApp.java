import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

// Creates a synthetic portfolio and compares sequential and Fork/Join totals.
public class ForkJoinPortfolioApp {
    private static final Scanner input = new Scanner(System.in);
    private static final int ASSET_COUNT = 10000;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Fork/Join Portfolio Assessment ===");
            System.out.println("1. Run portfolio risk assessment");
            System.out.println("2. Exit");
            int choice = readInt("Choose an option: ", 1, 2);
            if (choice == 2) {
                System.out.println("\nPortfolio application closed.");
                break;
            }

            double riskCap = readPositiveDouble("Enter risk cap (for example, 600): ");
            double[] values = createPortfolio();
            ForkJoinPool pool = new ForkJoinPool();

            long sequentialStart = System.nanoTime();
            double sequentialSum = 0.0;
            for (int i = 0; i < values.length; i++) {
                sequentialSum += values[i];
            }
            long sequentialNanos = System.nanoTime() - sequentialStart;

            long parallelStart = System.nanoTime();
            double parallelSum = pool.invoke(new ParallelRiskCalculator(values, 0, values.length));
            long parallelNanos = System.nanoTime() - parallelStart;

            long stressStart = System.nanoTime();
            pool.invoke(new PortfolioThresholdTask(values, 0, values.length, riskCap));
            long stressNanos = System.nanoTime() - stressStart;
            double adjustedSum = 0.0;
            for (int i = 0; i < values.length; i++)
                adjustedSum += values[i];

            System.out.println("\nAssets processed: " + ASSET_COUNT);
            System.out.printf("Sequential risk total: %.2f%n", sequentialSum);
            System.out.printf("Fork/Join risk total: %.2f%n", parallelSum);
            System.out.printf("Sequential sum time: %.3f ms%n", sequentialNanos / 1_000_000.0);
            System.out.printf("Fork/Join sum time: %.3f ms%n", parallelNanos / 1_000_000.0);
            if (parallelNanos > 0) {
                System.out.printf("Sequential / parallel speedup ratio: %.3f%n",
                        (double) sequentialNanos / parallelNanos);
            }
            System.out.printf("Stress-adjustment time: %.3f ms%n", stressNanos / 1_000_000.0);
            System.out.printf("Total after 5%% stress penalty above cap %.2f: %.2f%n", riskCap, adjustedSum);
            System.out.println("Note: parallel speed varies with CPU load and JVM warm-up.");
            pool.shutdown();
        }
    }

    private static double[] createPortfolio() {
        double[] values = new double[ASSET_COUNT];
        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = 50.0 + random.nextDouble() * 950.0;
        }
        return values;
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

    private static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(input.nextLine().trim());
                if (Double.isFinite(value) && value > 0)
                    return value;
            } catch (NumberFormatException ignored) {
                // Handle invalid values below.
            }
            System.out.println("Please enter a valid number greater than zero.");
        }
    }
}