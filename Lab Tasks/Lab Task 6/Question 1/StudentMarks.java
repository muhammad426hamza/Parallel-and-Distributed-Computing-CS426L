import java.util.HashMap;
import java.util.Scanner;

public class StudentMarks {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, Integer> students = new HashMap<String, Integer>();

        // Enter five student records
        System.out.println("====================================");
        System.out.println("       ENTER FIVE STUDENTS");
        System.out.println("====================================");

        while (students.size() < 5) {
            System.out.println("\nStudent " + (students.size() + 1));
            String name = readStudentName(input, "Enter student name: ");

            if (students.containsKey(name)) {
                System.out.println("This student already exists. Enter a different name.");
            } else {
                int marks = readMarks(input);
                students.put(name, marks);
            }
        }

        int choice;
        do {
            System.out.println("\n====================================");
            System.out.println("                MENU");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Student Records");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Search Student Marks");
            System.out.println("5. Display Total Students");
            System.out.println("6. Exit");
            System.out.println("====================================");

            choice = readChoice(input, 1, 6);

            if (choice == 1) {
                String name = readStudentName(input, "Enter student name: ");

                if (students.containsKey(name)) {
                    System.out.println("Student already exists.");
                } else {
                    int marks = readMarks(input);
                    students.put(name, marks);
                    System.out.println("Student added successfully.");
                }
            } else if (choice == 2) {
                System.out.println("\n======= RESULT / HASHMAP =======");
                if (students.size() == 0) {
                    System.out.println("No student records found.");
                } else {
                    for (String name : students.keySet()) {
                        System.out.println(name + " : " + students.get(name));
                    }
                }
            } else if (choice == 3) {
                String name = readStudentName(input, "Enter the student name to update: ");
                String actualName = findStudentName(students, name);

                if (actualName == null) {
                    System.out.println("Student not found.");
                } else {
                    int marks = readMarks(input);
                    students.put(actualName, marks);
                    System.out.println("Updated marks of " + actualName + ": " + students.get(actualName));
                }
            } else if (choice == 4) {
                String name = readStudentName(input, "Enter the student name to search: ");
                String actualName = findStudentName(students, name);

                if (actualName == null) {
                    System.out.println("Student not found.");
                } else {
                    System.out.println("Marks of " + actualName + ": " + students.get(actualName));
                }
            } else if (choice == 5) {
                System.out.println("Total number of students: " + students.size());
            } else if (choice == 6) {
                System.out.println("\nProgram ended.");
            }
        } while (choice != 6);

        input.close();
    }

    public static String readStudentName(Scanner input, String message) {
        System.out.print(message);
        String name = input.nextLine().trim();

        while (name.length() == 0) {
            System.out.print("Name cannot be empty. Enter name again: ");
            name = input.nextLine().trim();
        }
        return name;
    }

    public static int readMarks(Scanner input) {
        int marks;
        while (true) {
            System.out.print("Enter marks (0-100): ");
            String value = input.nextLine().trim();

            try {
                marks = Integer.parseInt(value);
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
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
                System.out.println("Please select a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number.");
            }
        }
    }

    public static String findStudentName(HashMap<String, Integer> students, String searchName) {
        for (String name : students.keySet()) {
            if (name.equalsIgnoreCase(searchName)) {
                return name;
            }
        }
        return null;
    }
}