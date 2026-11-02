package restaurantrush;

/** Owner: P4. One customer's order. Its price is locked when it is taken. */
public class Order {
    private final Customer customer;
    private final MenuItem item;
    private final int priceCents;
    private int unitsDone;
    private int readyOnTurn = -1;
    private OrderStatus status = OrderStatus.PLACED;

    public Order(Customer customer, MenuItem item, int priceCents) {
        this.customer = customer;
        this.item = item;
        this.priceCents = priceCents;
    }

    public Customer getCustomer() { return customer; }
    public MenuItem getItem() { return item; }
    public int getPriceCents() { return priceCents; }
    public OrderStatus getStatus() { return status; }

    public void advance(int turn) { /* TODO (P4): one prep unit; READY + readyOnTurn when done */ }
    /** Ready on an EARLIER turn (a dish finished this turn is served next turn at the earliest). */
    public boolean isServable(int turn) { return status == OrderStatus.READY && readyOnTurn < turn; }
    public void markServed() { /* TODO (P4) */ }
    public void markPaid() { /* TODO (P4) */ }
    public void cancel() { /* TODO (P4): CANCELLED unless already paid */ }
}
