import java.util.Scanner;

public class Q3_DisplayMatrix {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int[][] matrix = new int[4][4];
        int number = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = number;
                number++;
            }
        }

        int choice;

        while (true) {
            System.out.println("\n====================================");
            System.out.println("          4 x 4 MATRIX VIEWER");
            System.out.println("====================================");
            System.out.println("1. Display Matrix");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                System.out.println("\n4 x 4 Matrix:");

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.print(matrix[i][j] + "\t");
                    }
                    System.out.println();
                }
            } else if (choice == 2) {
                System.out.println("Program ended. Thank you!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        input.close();
    }
}