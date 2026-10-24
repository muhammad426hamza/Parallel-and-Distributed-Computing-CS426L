import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStudentMap {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        HashMap<String, Integer> normalMap = new HashMap<String, Integer>();
        final Map<String, Integer> students = Collections.synchronizedMap(normalMap);

        final int numberOfThreads = 3;
        final int recordsPerThread = 100;
        boolean recordsInserted = false;
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("                MENU");
            System.out.println("====================================");
            System.out.println("1. Insert Records Using 3 Threads");
            System.out.println("2. Display Result / HashMap Size");
            System.out.println("3. Exit");
            System.out.println("====================================");

            choice = readChoice(input, 1, 3);

            if (choice == 1) {
                students.clear();

                ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

                // Each thread inserts 100 student records
                for (int t = 1; t <= numberOfThreads; t++) {
                    final int threadNumber = t;

                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i <= recordsPerThread; i++) {
                                String name = "Student_" + threadNumber + "_" + i;
                                int marks = i % 101;

                                students.put(name, marks);
                            }
                        }
                    });
                }

                executor.shutdown();

                while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.out.println("Waiting for threads to finish...");
                }

                recordsInserted = true;
                System.out.println("\nAll threads have finished.");

                displayResult(students, numberOfThreads, recordsPerThread);

            } else if (choice == 2) {
                if (!recordsInserted) {
                    System.out.println(
                            "No records yet. Choose option 1 first.");
                } else {
                    displayResult(students, numberOfThreads, recordsPerThread);
                }

            } else if (choice == 3) {
                System.out.println("\nProgram ended.");
            }

        } while (choice != 3);

        input.close();
    }

    public static void displayResult(Map<String, Integer> students,
            int numberOfThreads, int recordsPerThread) {

        int expectedEntries = numberOfThreads * recordsPerThread;

        System.out.println("\n======= RESULT / SYNCHRONIZED MAP =======");
        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Records per thread: " + recordsPerThread);
        System.out.println("Expected entries: " + expectedEntries);
        System.out.println("Actual entries: " + students.size());
    }

    public static int readChoice(Scanner input, int min, int max) {
        int choice;

        while (true) {
            System.out.print("Enter your choice: ");
            String value = input.nextLine().trim();

            try {
                choice = Integer.parseInt(value);

                if (choice >= min && choice <= max) {
                    return choice;
                }

                System.out.println(
                        "Please select a number from " + min + " to " + max + ".");

            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number.");
            }
        }
    }
}