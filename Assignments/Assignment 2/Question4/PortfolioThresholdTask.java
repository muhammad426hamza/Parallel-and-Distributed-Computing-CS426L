import java.util.concurrent.RecursiveAction;

// Fork/Join task that raises assets above the risk cap by a 5% stress penalty.
public class PortfolioThresholdTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    private double[] values;
    private int start;
    private int end;
    private double riskCap;

    public PortfolioThresholdTask(double[] values, int start, int end, double riskCap) {
        this.values = values;
        this.start = start;
        this.end = end;
        this.riskCap = riskCap;
    }

    @Override
    protected void compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (values[i] > riskCap) {
                    values[i] = values[i] * 1.05; // Increase values over the cap by 5%.
                }
            }
            return;
        }

        int middle = start + length / 2;
        PortfolioThresholdTask leftTask = new PortfolioThresholdTask(values, start, middle, riskCap);
        PortfolioThresholdTask rightTask = new PortfolioThresholdTask(values, middle, end, riskCap);
        invokeAll(leftTask, rightTask);
    }
}