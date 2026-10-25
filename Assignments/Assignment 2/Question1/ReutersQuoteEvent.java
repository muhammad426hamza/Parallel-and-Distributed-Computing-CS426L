// ReutersQuoteEvent represents a market quote containing a bid and an ask price.
public class ReutersQuoteEvent extends TradeEvent {
    private double bidPrice;
    private double askPrice;

    public ReutersQuoteEvent(String senderId, long timestamp, double bidPrice, double askPrice) {
        super(senderId, timestamp, (bidPrice + askPrice) / 2.0);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    // Basic behaviour shared by all event types.
    public void dispatch() {
        System.out.printf("Generic event | Sender: %s | Time: %d | Base value: %.2f%n", senderId, timestamp, basePrice);
    }
}