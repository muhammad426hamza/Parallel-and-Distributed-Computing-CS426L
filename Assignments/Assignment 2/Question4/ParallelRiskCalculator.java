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

    @Override
    protected Double compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            double sum = 0.0;
            for (int i = start; i < end; i++) {
                sum += values[i];
            }
            return sum;
        }

        int middle = start + length / 2;
        ParallelRiskCalculator leftTask = new ParallelRiskCalculator(values, start, middle);
        ParallelRiskCalculator rightTask = new ParallelRiskCalculator(values, middle, end);
        leftTask.fork();
        rightTask.fork();
        double leftSum = leftTask.join();
        double rightSum = rightTask.join();
        return leftSum + rightSum;
    }
}