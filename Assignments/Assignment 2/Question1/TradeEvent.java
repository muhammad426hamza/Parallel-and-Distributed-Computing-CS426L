// Parent class for different types of trading events.
public class TradeEvent {
    protected String senderId;
    protected long timestamp;
    protected double basePrice;

    public TradeEvent(String senderId, long timestamp, double basePrice) {
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.basePrice = basePrice;
    }

    // Basic behaviour shared by all event types.
    public void dispatch() {
        System.out.printf("Generic event | Sender: %s | Time: %d | Base value: %.2f%n",
                senderId, timestamp, basePrice);
    }
}