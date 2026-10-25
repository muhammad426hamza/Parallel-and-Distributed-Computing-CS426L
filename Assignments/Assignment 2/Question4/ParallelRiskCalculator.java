import java.util.concurrent.RecursiveTask;

// Fork/Join task that returns the sum of one part of the asset array.
public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private static final int THRESHOLD = 1000;
    private double[] values;
    private int start;
    private int end; // End index is not included.

    public ParallelRiskCalculator(double[] values, int start, int end) {
        this.values = values;
        this.start = start;
        this.end = end;
    }
}
