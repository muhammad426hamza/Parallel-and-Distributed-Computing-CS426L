// Each worker searches the same assigned segment for both minimum and maximum.
public class SensorRangeWorker extends Thread {
    private ParallelMinSearch minSearch;
    private ParallelMaxSearch maxSearch;
    private double minimum;
    private double maximum;

    public SensorRangeWorker(String name, ParallelMinSearch minSearch, ParallelMaxSearch maxSearch) {
        super(name);
        this.minSearch = minSearch;
        this.maxSearch = maxSearch;
    }

    @Override
    public void run() {
        minimum = minSearch.processGrid();
        maximum = maxSearch.processGrid();
        System.out.println(getName() + " finished its sensor segment.");
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }
}