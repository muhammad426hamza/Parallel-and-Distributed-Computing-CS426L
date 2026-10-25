// This class extends Thread directly to report telemetry values.
public class TelemetryThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + getName() + "] CPU and memory health update " + i + "/5");
            try {
                Thread.sleep(800); // Pause for 800 milliseconds.
            } catch (InterruptedException e) {
                interrupt();
                System.out.println("Telemetry worker was interrupted.");
                return;
            }
        }
    }
}