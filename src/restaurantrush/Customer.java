package restaurantrush;

/**
 * Owner: P3. A customer. Satisfaction starts at 100 and is kept in 0..100.
 * The methods GameController and the staff actions call are listed below.
 */
public abstract class Customer {
    private final int id;
    private final int arrivalTurn;
    private int satisfaction = 100;
    private CustomerState state = CustomerState.WAITING_IN_QUEUE;
    private int servedOnTurn = -1;
    private Order order;

    protected Customer(int id, int arrivalTurn) {
        this.id = id;
        this.arrivalTurn = arrivalTurn;
    }

    public int getId() { return id; }
    public int getArrivalTurn() { return arrivalTurn; }
    public int getSatisfaction() { return satisfaction; }
    public CustomerState getState() { return state; }
    public int getServedOnTurn() { return servedOnTurn; }
    /** The customer's order, or null before they order. */
    public Order getOrder() { return order; }

    public void markSeated() { /* TODO (P3): state -> SEATED */ }
    public void placeOrder(Order o) { /* TODO (P3): remember o, state -> ORDERED */ }
    public void markServed(int turn) { /* TODO (P3): servedOnTurn = turn, state -> EATING */ }
    public void finishEating(int turn) { /* TODO (P3): state -> READY_TO_PAY */ }
    public void markPaid() { /* TODO (P3): state -> PAID */ }
    public void leave() { /* TODO (P3): state -> LEFT_UNHAPPY */ }

    public void loseSatisfaction() { /* TODO (P3): satisfaction = clamp(satisfaction - waitLoss()) */ }
    public void recover(int points) { /* TODO (P3): satisfaction = clamp(satisfaction + points) */ }
    private int clamp(int v) { return Math.max(0, Math.min(100, v)); }

    /** How much satisfaction this customer type loses per waiting turn. */
    public abstract int waitLoss();

    /** Default: full price. VIP overrides (Stage 2). */
    public int adjustBill(int cents) { return cents; }

    /** The customer chooses their own food (documented deterministic rule). */
    public MenuItem chooseItem(Menu menu, int turn) {
        // TODO (P3)
        throw new UnsupportedOperationException("TODO: P3");
    }

    /** Hook called by the Cashier just before payment. Default: nothing. */
    public void onPaid(Order o) { }
}
