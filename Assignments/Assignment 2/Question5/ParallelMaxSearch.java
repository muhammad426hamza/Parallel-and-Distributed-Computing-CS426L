// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
public class ParallelMaxSearch extends SensorSearchEngine {
    public ParallelMaxSearch(double[] var1, int var2, int var3) {
        super(var1, var2, var3);
    }

    public double processGrid() {
        double var1 = this.sensorData[this.startIdx];

        for (int var3 = this.startIdx + 1; var3 < this.endIdx; ++var3) {
            if (this.sensorData[var3] > var1) {
                var1 = this.sensorData[var3];
            }
        }

        return var1;
    }
}