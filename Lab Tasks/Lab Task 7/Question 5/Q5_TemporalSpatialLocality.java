import java.util.Random;
import java.util.Scanner;

public class Q5_TemporalSpatialLocality {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("          MEMORY LOCALITY DEMO");
            System.out.println("====================================");
            System.out.println("1. Demonstrate Temporal Locality");
            System.out.println("2. Demonstrate Spatial Locality");
            System.out.println("3. Compare Both Access Patterns");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 3) {

                System.out.print("Enter array size: ");
                int size = input.nextInt();

                System.out.print("Enter number of repetitions: ");
                int repetitions = input.nextInt();

                if (size <= 0 || repetitions <= 0) {
                    System.out.println("Values must be greater than zero.");
                } else {
                    int[] array = new int[size];
                    Random random = new Random();

                    for (int i = 0; i < size; i++) {
                        array[i] = random.nextInt(100) + 1;
                    }

                    if (choice == 1) {

                        long startTime = System.nanoTime();

                        long total = temporalLocality(array, repetitions);

                        long endTime = System.nanoTime();

                        System.out.println("\nTemporal Locality Demonstration");
                        System.out.println("Same element accessed repeatedly.");
                        System.out.println("Total accesses: " + (long) size * repetitions);
                        System.out.println("Total value: " + total);
                        System.out.println("Execution time: "
                                + (endTime - startTime) / 1000000.0 + " ms");
                    }

                    else if (choice == 2) {

                        long startTime = System.nanoTime();

                        long total = spatialLocality(array, repetitions);

                        long endTime = System.nanoTime();

                        System.out.println("\nSpatial Locality Demonstration");
                        System.out.println("Consecutive elements accessed.");
                        System.out.println("Total accesses: " + (long) size * repetitions);
                        System.out.println("Total value: " + total);
                        System.out.println("Execution time: "
                                + (endTime - startTime) / 1000000.0 + " ms");
                    }

                    else if (choice == 3) {

                        temporalLocality(array, 1);
                        spatialLocality(array, 1);

                        long startTime = System.nanoTime();
                        long temporalTotal = temporalLocality(array, repetitions);
                        long endTime = System.nanoTime();

                        double temporalTime = (endTime - startTime) / 1000000.0;

                        startTime = System.nanoTime();
                        long spatialTotal = spatialLocality(array, repetitions);
                        endTime = System.nanoTime();

                        double spatialTime = (endTime - startTime) / 1000000.0;

                        System.out.println("\nComparison of Access Patterns");
                        System.out.println("Total accesses: "
                                + (long) size * repetitions);
                        System.out.println("Temporal locality time: "
                                + temporalTime + " ms");
                        System.out.println("Spatial locality time: "
                                + spatialTime + " ms");
                        System.out.println("Temporal total value: " + temporalTotal);
                        System.out.println("Spatial total value: " + spatialTotal);
                        System.out.println("Both methods use the same array.");
                    }
                }
            }

            else if (choice == 4) {
                System.out.println("Program ended. Thank you!");
            }

            else {
                System.out.println("Invalid choice. Please enter 1 to 4.");
            }

        } while (choice != 4);

        input.close();
    }

    public static long temporalLocality(int[] array, int repetitions) {

        long total = 0;

        for (int r = 0; r < repetitions; r++) {
            for (int i = 0; i < array.length; i++) {
                total = total + array[0];
            }
        }

        return total;
    }

    public static long spatialLocality(int[] array, int repetitions) {

        long total = 0;

        for (int r = 0; r < repetitions; r++) {
            for (int i = 0; i < array.length; i++) {
                total = total + array[i];
            }
        }

        return total;
    }
}