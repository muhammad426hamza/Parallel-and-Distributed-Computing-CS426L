import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

// Creates a synthetic portfolio and compares sequential and Fork/Join totals.
public class ForkJoinPortfolioApp {
    private static final Scanner input = new Scanner(System.in);
    private static final int ASSET_COUNT = 10000;

    public static void main(String[] args) {
        
    }

    private static double[] createPortfolio() {
        double[] values = new double[ASSET_COUNT];
        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = 50.0 + random.nextDouble() * 950.0;
        }
        return values;
    }
}