import java.util.Random;
import java.util.Scanner;

public class Q2_CompareLoopOrders {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       COMPARE MATRIX LOOP ORDERS");
            System.out.println("====================================");
            System.out.println("1. Compare all six orders (2048 x 2048)");
            System.out.println("2. Test all six orders with a smaller size");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();

            if (choice == 1) {
                compareOrders(2048);
            } else if (choice == 2) {
                System.out.print("Enter matrix size: ");
                int n = input.nextInt();
                if (n > 0) {
                    compareOrders(n);
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

    public static void compareOrders(int n) {
        Random random = new Random();
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];
        int[][] answer = new int[n][n];
        double[] times = new double[6];
        String[] names = { "i-j-k", "i-k-j", "j-i-k", "j-k-i", "k-i-j", "k-j-i" };

        System.out.println("\nCreating the same input matrices for all six tests...");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt(10) + 1;
                b[i][j] = random.nextInt(10) + 1;
            }
        }

        // First order is used as the reference result.
        clearMatrix(c);
        long startTime = System.nanoTime();
        multiplyIJK(a, b, c);
        long endTime = System.nanoTime();
        times[0] = (endTime - startTime) / 1000000000.0;
        copyMatrix(c, answer);

        // Run the other five orders using the same A and B matrices.
        clearMatrix(c);
        startTime = System.nanoTime();
        multiplyIKJ(a, b, c);
        endTime = System.nanoTime();
        times[1] = (endTime - startTime) / 1000000000.0;
        showCorrectness(c, answer, "i-k-j");

        clearMatrix(c);
        startTime = System.nanoTime();
        multiplyJIK(a, b, c);
        endTime = System.nanoTime();
        times[2] = (endTime - startTime) / 1000000000.0;
        showCorrectness(c, answer, "j-i-k");

        clearMatrix(c);
        startTime = System.nanoTime();
        multiplyJKI(a, b, c);
        endTime = System.nanoTime();
        times[3] = (endTime - startTime) / 1000000000.0;
        showCorrectness(c, answer, "j-k-i");

        clearMatrix(c);
        startTime = System.nanoTime();
        multiplyKIJ(a, b, c);
        endTime = System.nanoTime();
        times[4] = (endTime - startTime) / 1000000000.0;
        showCorrectness(c, answer, "k-i-j");

        clearMatrix(c);
        startTime = System.nanoTime();
        multiplyKJI(a, b, c);
        endTime = System.nanoTime();
        times[5] = (endTime - startTime) / 1000000000.0;
        showCorrectness(c, answer, "k-j-i");

        System.out.println("\nExecution Time Comparison");
        System.out.println("Matrix size: " + n + " x " + n);
        System.out.println("Loop Order\tTime (seconds)");
        System.out.println("--------------------------------");
        for (int i = 0; i < times.length; i++) {
            System.out.println(names[i] + "\t\t" + times[i]);
        }

        int fastest = 0;
        int slowest = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[fastest]) {
                fastest = i;
            }
            if (times[i] > times[slowest]) {
                slowest = i;
            }
        }

        System.out.println("\nFastest in this run: " + names[fastest]);
        System.out.println("Slowest in this run: " + names[slowest]);
        System.out.println("All six tests used the same input matrices.");
        System.out.println("The output matrix was reset before every multiplication.");
        System.out.println("Actual results can change between computers and runs.");
    }

    public static void showCorrectness(int[][] result, int[][] answer, String name) {
        System.out.println(name + " result correct: " + sameMatrix(result, answer));
    }

    public static void clearMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public static void copyMatrix(int[][] source, int[][] destination) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }

    public static boolean sameMatrix(int[][] first, int[][] second) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                if (first[i][j] != second[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void multiplyIJK(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
    }

    public static void multiplyIKJ(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
    }

    public static void multiplyJIK(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
    }

    public static void multiplyJKI(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                int value = b[k][j];
                for (int i = 0; i < n; i++) {
                    c[i][j] = c[i][j] + a[i][k] * value;
                }
            }
        }
    }

    public static void multiplyKIJ(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                int value = a[i][k];
                for (int j = 0; j < n; j++) {
                    c[i][j] = c[i][j] + value * b[k][j];
                }
            }
        }
    }

    public static void multiplyKJI(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                int value = b[k][j];
                for (int i = 0; i < n; i++) {
                    c[i][j] = c[i][j] + a[i][k] * value;
                }
            }
        }
    }
}