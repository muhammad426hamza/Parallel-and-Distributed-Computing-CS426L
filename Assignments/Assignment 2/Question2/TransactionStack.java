// Fixed-capacity array stack using the Last-In, First-Out (LIFO) rule.
public class TransactionStack {
    private char[] data;
    private int top;

    public TransactionStack(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive.");
        data = new char[capacity];
        top = -1;
    }
}