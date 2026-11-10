import java.util.Random;
import java.util.Scanner;

public class Q6_DifferentMatrixSizes {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("      MATRIX SIZE PERFORMANCE TEST");
            System.out.println("====================================");
            System.out.println("1. Multiply 128 x 128 Matrices");
            System.out.println("2. Multiply 256 x 256 Matrices");
            System.out.println("3. Multiply 512 x 512 Matrices");
            System.out.println("4. Multiply 1024 x 1024 Matrices");
            System.out.println("5. Compare All Matrix Sizes");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 4) {
                int[] sizes = { 128, 256, 512, 1024 };
                int n = sizes[choice - 1];

                System.out.println("\nMultiplying " + n + " x " + n + " matrices...");
                double time = multiplyForSize(n);
                System.out.printf("Execution time: %.6f seconds%n", time);
            } else if (choice == 5) {
                int[] sizes = { 128, 256, 512, 1024 };
                double[] times = new double[4];

                System.out.println("\nRunning all required matrix sizes...");

                for (int i = 0; i < sizes.length; i++) {
                    System.out.println("\nMultiplying " + sizes[i] + " x " + sizes[i] + " matrices...");
                    times[i] = multiplyForSize(sizes[i]);
                }

                System.out.println("\n===== Matrix Size Comparison =====");
                System.out.println("Matrix Size\tExecution Time (seconds)");
                System.out.println("------------------------------------------");

                for (int i = 0; i < sizes.length; i++) {
                    System.out.printf("%d x %d\t\t%.6f%n",
                            sizes[i], sizes[i], times[i]);
                }
            } else if (choice == 6) {
                System.out.println("Program ended. Thank you!");
            } else {
                System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }

        } while (choice != 6);

        input.close();
    }

    public static double multiplyForSize(int n) {

        Random random = new Random();
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt(10) + 1;
                b[i][j] = random.nextInt(10) + 1;
                c[i][j] = 0;
            }
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }

        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1000000000.0;

        System.out.println("Sample result C[0][0]: " + c[0][0]);

        return seconds;
    }
}