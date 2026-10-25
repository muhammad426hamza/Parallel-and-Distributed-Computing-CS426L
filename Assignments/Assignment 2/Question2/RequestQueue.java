// Fixed-capacity array queue using the First-In, First-Out (FIFO) rule.
public class RequestQueue {
    private char[] data;
    private int front;
    private int rear;
    private int size;

    public RequestQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive.");
        data = new char[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
}