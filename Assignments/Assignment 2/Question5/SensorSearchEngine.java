// Parent class for searching one segment of sensor readings.
public abstract class SensorSearchEngine {
    protected double[] sensorData;
    protected int startIdx;
    protected int endIdx; // End index is not included.

    public SensorSearchEngine(double[] sensorData, int startIdx, int endIdx) {
        if (sensorData == null || startIdx < 0 || endIdx > sensorData.length || startIdx >= endIdx) {
            throw new IllegalArgumentException("Invalid sensor data range.");
        }
        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public abstract double processGrid();
}