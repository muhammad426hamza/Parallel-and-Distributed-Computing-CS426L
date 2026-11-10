import java.util.Random;
import java.util.Scanner;

public class Q1_MatrixMultiplication {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       MATRIX MULTIPLICATION");
            System.out.println("====================================");
            System.out.println("1. Run required multiplication (2048 x 2048)");
            System.out.println("2. Test with a smaller/custom matrix size");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();

            if (choice == 1) {
                multiplyMatrices(2048);
            } else if (choice == 2) {
                System.out.print("Enter matrix size: ");
                int n = input.nextInt();
                if (n > 0) {
                    multiplyMatrices(n);
                } else {
                    System.out.println("Please enter a size greater than zero.");
                }
            } else if (choice == 0) {
                System.out.println("Program closed.");
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        } while (choice != 0);

        input.close();
    }

    public static void multiplyMatrices(int n) {
        Random random = new Random();
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];

        System.out.println("\nPreparing " + n + " x " + n + " matrices...");

        // Fill the first two matrices with random numbers.
        // The third matrix starts with zeros automatically in Java.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt(10) + 1;
                b[i][j] = random.nextInt(10) + 1;
            }
        }

        System.out.println("Multiplication using i -> j -> k has started.");
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

        System.out.println("\nMultiplication completed successfully.");
        System.out.println("Matrix size: " + n + " x " + n);
        System.out.println("Execution time: " + seconds + " seconds");
        System.out.println("Sample result C[0][0]: " + c[0][0]);
    }
}