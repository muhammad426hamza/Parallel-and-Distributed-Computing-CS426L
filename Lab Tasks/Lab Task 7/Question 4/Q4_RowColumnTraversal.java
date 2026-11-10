import java.util.Random;
import java.util.Scanner;

public class Q4_RowColumnTraversal {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       ARRAY TRAVERSAL COMPARISON");
            System.out.println("====================================");
            System.out.println("1. Compare Row-wise and Column-wise Traversal");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                System.out.print("Enter array size: ");
                int n = input.nextInt();

                System.out.print("Enter number of repetitions: ");
                int repetitions = input.nextInt();

                if (n > 0 && repetitions > 0) {
                    compareTraversal(n, repetitions);
                } else {
                    System.out.println("Array size and repetitions must be greater than zero.");
                }
            } else if (choice == 2) {
                System.out.println("Program ended. Thank you!");
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }

        } while (choice != 2);

        input.close();
    }

    public static void compareTraversal(int n, int repetitions) {

        Random random = new Random();
        int[][] array = new int[n][n];

        System.out.println("\nCreating a " + n + " x " + n + " array...");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = random.nextInt(10) + 1;
            }
        }

        long rowTotalTime = 0;
        long columnTotalTime = 0;
        long rowSum = 0;
        long columnSum = 0;

        for (int r = 0; r < repetitions; r++) {

            long startTime = System.nanoTime();

            rowSum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rowSum = rowSum + array[i][j];
                }
            }

            long endTime = System.nanoTime();
            rowTotalTime = rowTotalTime + (endTime - startTime);

            startTime = System.nanoTime();

            columnSum = 0;
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    columnSum = columnSum + array[i][j];
                }
            }

            endTime = System.nanoTime();
            columnTotalTime = columnTotalTime + (endTime - startTime);
        }

        double rowAverage = rowTotalTime / (double) repetitions / 1000000.0;
        double columnAverage = columnTotalTime / (double) repetitions / 1000000.0;

        System.out.println("\nArray size: " + n + " x " + n);
        System.out.println("Number of repetitions: " + repetitions);
        System.out.println("Row-wise average time: " + rowAverage + " ms");
        System.out.println("Column-wise average time: " + columnAverage + " ms");
        System.out.println("Row-wise sum: " + rowSum);
        System.out.println("Column-wise sum: " + columnSum);
        System.out.println("Both sums are equal: " + (rowSum == columnSum));
    }
}