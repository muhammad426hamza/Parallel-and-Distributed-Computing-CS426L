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
}