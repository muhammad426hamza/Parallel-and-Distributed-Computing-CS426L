// FIXOrderEvent is a specialized trading event for a stock purchase order.
public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp, double orderValue, String clOrdId) {
        super(senderId, timestamp, orderValue);
        this.clOrdId = clOrdId;
    }

    // Overrides the parent's dispatch method to show the order and its fee.
    @Override
    public void dispatch() {
        double fee = basePrice * 0.0015; // 0.15 percent exchange fee
        double total = basePrice + fee;
        System.out.println("FIX STOCK PURCHASE ORDER");
        System.out.println("Sender ID: " + senderId);
        System.out.println("Client Order ID: " + clOrdId);
        System.out.printf("Order value: %.2f%n", basePrice);
        System.out.printf("Exchange fee (0.15%%): %.2f%n", fee);
        System.out.printf("Total value: %.2f%n", total);
    }
}