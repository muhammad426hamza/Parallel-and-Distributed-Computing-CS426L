import java.util.Random;
import java.util.Scanner;

// Generates 50,000 readings and searches four segments using four worker threads.
public class SensorGridMonitorApp {
    private static final Scanner input = new Scanner(System.in);
    private static final int DATA_SIZE = 50000;
    private static final int WORKER_COUNT = 4;

    public static void main(String[] args) {
        
    }

    private static double[] createSensorData(double low, double high) {
        double[] data = new double[DATA_SIZE];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = low + random.nextDouble() * (high - low);
        }
        return data;
    }
}