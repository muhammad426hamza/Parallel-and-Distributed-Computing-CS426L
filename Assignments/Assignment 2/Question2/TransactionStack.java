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

    public void push(char c) {
        if (top == data.length - 1)
            throw new IllegalStateException("Stack is full.");
        top++;
        data[top] = c;
    }

    public char pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty.");
        char value = data[top];
        top--;
        return value;
    }

    public char peek() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty.");
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}