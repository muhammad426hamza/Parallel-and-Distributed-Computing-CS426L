// ReutersQuoteEvent represents a market quote containing a bid and an ask price.
public class ReutersQuoteEvent extends TradeEvent {
    private double bidPrice;
    private double askPrice;

    public ReutersQuoteEvent(String senderId, long timestamp, double bidPrice, double askPrice) {
        super(senderId, timestamp, (bidPrice + askPrice) / 2.0);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    // Overrides dispatch() to display a simple bid-ask analysis.
    @Override
    public void dispatch() {
        double spread = askPrice - bidPrice;
        double spreadPercent = (spread / bidPrice) * 100.0;
        System.out.println("\n====(REUTERS MARKET QUOTE)====");
        System.out.println("Sender ID: " + senderId);
        System.out.println("Timestamp: " + timestamp);
        System.out.printf("Bid price: %.2f%n", bidPrice);
        System.out.printf("Ask price: %.2f%n", askPrice);
        System.out.printf("Bid-ask spread: %.2f%n", spread);
        System.out.printf("Spread percentage: %.3f%%%n", spreadPercent);
        System.out.println("================================");
    }
}