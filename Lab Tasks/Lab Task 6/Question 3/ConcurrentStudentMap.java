import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentStudentMap {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        final ConcurrentHashMap<String, Integer> students = new ConcurrentHashMap<String, Integer>();

        final int numberOfThreads = 5;
        final int recordsPerThread = 1000;

        boolean recordsInserted = false;
        int lastRetrievedRecords = 0;
        long lastExecutionTime = 0;
        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("                MENU");
            System.out.println("====================================");
            System.out.println("1. Insert and Retrieve Records");
            System.out.println("2. Display Result / ConcurrentHashMap");
            System.out.println("3. Exit");
            System.out.println("====================================");

            choice = readChoice(input, 1, 3);

            if (choice == 1) {
                students.clear();

                ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

                long startTime = System.currentTimeMillis();

                // Each thread inserts 1000 student records
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

                // Wait for all threads to finish
                executor.shutdown();

                while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.out.println("Waiting for threads to finish...");
                }

                // Retrieve the inserted records
                lastRetrievedRecords = 0;

                for (int t = 1; t <= numberOfThreads; t++) {
                    for (int i = 1; i <= recordsPerThread; i++) {
                        String name = "Student_" + t + "_" + i;
                        Integer marks = students.get(name);

                        if (marks != null) {
                            lastRetrievedRecords++;
                        }
                    }
                }

                long endTime = System.currentTimeMillis();
                lastExecutionTime = endTime - startTime;
                recordsInserted = true;

                displayResult(students, numberOfThreads, recordsPerThread,
                        lastRetrievedRecords, lastExecutionTime);

            } else if (choice == 2) {
                if (!recordsInserted) {
                    System.out.println(
                            "No records yet. Choose option 1 first.");
                } else {
                    displayResult(students, numberOfThreads, recordsPerThread,
                            lastRetrievedRecords, lastExecutionTime);
                }

            } else if (choice == 3) {
                System.out.println("\nProgram ended.");
            }

        } while (choice != 3);

        input.close();
    }

    public static void displayResult(ConcurrentHashMap<String, Integer> students, int numberOfThreads, int recordsPerThread,int retrievedRecords, long executionTime) {

        int expectedEntries = numberOfThreads * recordsPerThread;

        System.out.println("\n======= RESULT / CONCURRENTHASHMAP =======");
        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Expected entries: " + expectedEntries);
        System.out.println("Actual entries: " + students.size());
        System.out.println("Records retrieved: " + retrievedRecords);
        System.out.println("Execution time (milliseconds): " + executionTime);
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