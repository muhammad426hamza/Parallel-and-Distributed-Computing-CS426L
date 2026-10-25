import java.util.Scanner;

// Compares row-major and column-major matrix traversal using System.nanoTime().
public class CacheLocalityBenchmark {
    private static final Scanner input = new Scanner(System.in);
    private static final int N = 2000;
    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Cache Locality Benchmark ===");
            System.out.println("1. Run row-major and column-major benchmark");
            System.out.println("2. Exit");
            int choice = readInt("Choose an option: ", 1, 2);
            if (choice == 2) {
                System.out.println("\nCache benchmark closed.");
                break;
            }
            runBenchmark();
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
                // Handle invalid menu input below.
            }
            System.out.println("Please enter a whole number from " + min + " to " + max + ".");
        }
    }

    // Column-major order: values from different rows are visited for each column.
    private static double traverseColumnMajor(double[][] matrix) {
        double sum = 0.0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++)
                sum += matrix[i][j];
        }
        return sum;
    }

    // Row-major order: each row's adjacent values are visited together.
    private static double traverseRowMajor(double[][] matrix) {
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sum += matrix[i][j];
        }
        return sum;
    }

    private static void runBenchmark() {
        double[][] matrix = new double[N][N];
        // Fill the matrix once before measuring traversal time.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = i + j;
            }
        }

        double rowChecksum = 0.0;
        double columnChecksum = 0.0;
        long rowStart = System.nanoTime();
        for (int repeat = 0; repeat < ITERATIONS; repeat++) {
            rowChecksum += traverseRowMajor(matrix);
        }
        long rowNanos = System.nanoTime() - rowStart;

        long columnStart = System.nanoTime();
        for (int repeat = 0; repeat < ITERATIONS; repeat++) {
            columnChecksum += traverseColumnMajor(matrix);
        }
        long columnNanos = System.nanoTime() - columnStart;

        double rowMillis = rowNanos / 1_000_000.0;
        double columnMillis = columnNanos / 1_000_000.0;
        System.out.println("\nMatrix size: " + N + " x " + N);
        System.out.println("Repeated iterations per order: " + ITERATIONS);
        System.out.printf("Row-major total time: %.3f ms (average %.3f ms)%n",
                rowMillis, rowMillis / ITERATIONS);
        System.out.printf("Column-major total time: %.3f ms (average %.3f ms)%n",
                columnMillis, columnMillis / ITERATIONS);
        if (rowNanos > 0) {
            System.out.printf("Column time / row time ratio: %.3f%n", (double) columnNanos / rowNanos);
        }
        System.out.printf("Checksums: row %.1f | column %.1f%n", rowChecksum, columnChecksum);
        System.out.println("A ratio above 1 means row-major traversal was faster on this run.");
    }
}