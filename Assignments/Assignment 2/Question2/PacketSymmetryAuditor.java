import java.util.Scanner;

// Compares the reverse of a packet payload with its original character order.
public class PacketSymmetryAuditor {
    private static final Scanner input = new Scanner(System.in);
    private static final int MAX_PAYLOAD_LENGTH = 256;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Packet Symmetry Auditor ===");
            System.out.println("1. Check a packet payload");
            System.out.println("2. Exit");
            int choice = readInt("Choose an option: ", 1, 2);

            if (choice == 2) {
                System.out.println("Auditor closed.");
                break;
            }

            String payload = readPayload();
            TransactionStack stack = new TransactionStack(MAX_PAYLOAD_LENGTH);
            RequestQueue queue = new RequestQueue(MAX_PAYLOAD_LENGTH);

            // Store every character in both data structures.
            for (int i = 0; i < payload.length(); i++) {
                stack.push(payload.charAt(i));
                queue.enqueue(payload.charAt(i));
            }

            boolean symmetric = true;
            int position = 1;
            // Stack returns characters backward; queue returns them forward.
            while (!stack.isEmpty() && !queue.isEmpty()) {
                char fromStack = stack.pop();
                char fromQueue = queue.dequeue();
                if (fromStack != fromQueue) {
                    symmetric = false;
                    System.out.println("First mismatch found at comparison " + position + ".");
                    break;
                }
                position++;
            }

            System.out.println("Payload: " + payload);
            if (symmetric) {
                System.out.println("Result: SYMMETRIC (palindrome signature detected).");
            } else {
                System.out.println("Result: NOT SYMMETRIC.");
            }
        }
    }

    private static String readPayload() {
        while (true) {
            System.out.print("Enter payload (1-" + MAX_PAYLOAD_LENGTH + " characters): ");
            String value = input.nextLine();
            if (!value.trim().isEmpty() && value.length() <= MAX_PAYLOAD_LENGTH)
                return value;
            System.out.println("Payload must not be blank and must fit the fixed array capacity.");
        }
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
}