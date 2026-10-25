
// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.Scanner;

public class AsyncWebEngineApp {
    private static final Scanner input;

    public AsyncWebEngineApp() {
    }

    public static void main(String[] var0) {
        while (true) {
            System.out.println("\n=== Asynchronous Telemetry Engine ===");
            System.out.println("1. Start both worker threads");
            System.out.println("2. Exit");
            int var1 = readInt("Choose an option: ", 1, 2);
            if (var1 == 2) {
                System.out.println("Telemetry application closed.");
                return;
            }

            System.out.println("\nMain thread name: " + Thread.currentThread().getName());
            TelemetryThread var2 = new TelemetryThread();
            var2.setName("Telemetry-Worker");
            Thread var3 = new Thread(new LogProcessorTask(), "Log-Worker");
            var2.start();
            var3.start();
            System.out.println("Telemetry worker state: " + String.valueOf(var2.getState()));
            System.out.println("Log worker state: " + String.valueOf(var3.getState()));

            try {
                var2.join();
                var3.join();
            } catch (InterruptedException var5) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread was interrupted while waiting.");
            }

            System.out.println("Both worker threads have finished.");
        }
    }

    private static int readInt(String var0, int var1, int var2) {
        while (true) {
            System.out.print(var0);

            try {
                int var3 = Integer.parseInt(input.nextLine().trim());
                if (var3 >= var1 && var3 <= var2) {
                    return var3;
                }
            } catch (NumberFormatException var4) {
            }

            System.out.println("Please enter a whole number from " + var1 + " to " + var2 + ".");
        }
    }

    static {
        input = new Scanner(System.in);
    }
}