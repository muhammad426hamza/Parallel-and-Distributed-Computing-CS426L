import java.util.Scanner;

// Compares row-major and column-major matrix traversal using System.nanoTime().
public class CacheLocalityBenchmark {
    private static final Scanner input = new Scanner(System.in);
    private static final int N = 2000;
    private static final int ITERATIONS = 10;

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
}