
// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.Scanner;

public class AsyncWebEngineApp {
    private static final Scanner input;

    public AsyncWebEngineApp() {
    }

    public static void main(String[] var0) {
        
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