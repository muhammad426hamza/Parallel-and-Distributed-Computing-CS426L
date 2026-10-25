// FIXOrderEvent is a specialized trading event for a stock purchase order.
public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp, double orderValue, String clOrdId) {
        super(senderId, timestamp, orderValue);
        this.clOrdId = clOrdId;
    }

    // Basic behaviour shared by all event types.
    public void dispatch() {
        System.out.printf("Generic event | Sender: %s | Time: %d | Base value: %.2f%n", senderId, timestamp, basePrice);
    }
}