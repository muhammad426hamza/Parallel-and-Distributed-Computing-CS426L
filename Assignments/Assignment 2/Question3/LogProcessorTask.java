// Task used by a Thread object through the Runnable interface.
public class LogProcessorTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + Thread.currentThread().getName()
                    + "] Log analysis iteration " + i + "/5");
            try {
                Thread.sleep(1000); // Pause for one second between log messages.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Log worker was interrupted.");
                return;
            }
        }
    }
}