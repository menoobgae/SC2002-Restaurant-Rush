package restaurantrush;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.OptionalDouble;

/**
 * The restaurant's state: tables, waiting queue, staff, menu, pricing and the
 * money/satisfaction totals. It owns its Tables, Menu and Staff (composition)
 * and holds Customers in the queue without owning them (aggregation).
 *
 * Restaurant stores state and answers questions about it; the turn order and
 * game rules live in GameController and the staff actions.
 */
public class Restaurant {
    private final List<Table> tables = new ArrayList<>();
    private final Deque<Customer> queue = new ArrayDeque<>();
    private final List<Staff> staff = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>(); // everyone who has arrived
    private final Menu menu;
    private final PricingService pricing;

    private int revenueCents;
    private int paidCount;
    private int satisfactionSum;

    public Restaurant(int tableCount, Menu menu, PricingService pricing) {
        for (int i = 1; i <= tableCount; i++) {
            tables.add(new Table(i));
        }
        this.menu = menu;
        this.pricing = pricing;
    }

    // ---------------------------------------------------------------- queue

    /** A new arrival joins the back of the waiting queue. */
    public void enqueue(Customer c) {
        queue.addLast(c);
        customers.add(c);
    }

    /** The customer at the front of the queue, or null if nobody is waiting. */
    public Customer nextQueued() {
        return queue.peekFirst();
    }

    /** Removes a customer from the queue (when seated or when they leave). */
    public boolean removeFromQueue(Customer c) {
        return queue.remove(c);
    }

    /** Waiting customers, oldest first (read-only copy). */
    public List<Customer> getQueue() {
        return new ArrayList<>(queue);
    }

    // --------------------------------------------------------------- tables

    /** The first free table, or null if every table is occupied. */
    public Table freeTable() {
        for (Table t : tables) {
            if (t.isFree()) {
                return t;
            }
        }
        return null;
    }

    /** The table a customer is sitting at, or null if they are not seated. */
    public Table tableOf(Customer c) {
        for (Table t : tables) {
            if (t.getOccupant() == c) {
                return t;
            }
        }
        return null;
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    // ---------------------------------------------------------------- staff

    /** Staff act in the order they are registered (Host, then Waiter, then Chef). */
    public void registerStaff(Staff s) {
        staff.add(s);
    }

    public List<Staff> getStaff() {
        return Collections.unmodifiableList(staff);
    }

    // ------------------------------------------------------- menu & pricing

    public Menu getMenu() {
        return menu;
    }

    public PricingService getPricing() {
        return pricing;
    }

    // ---------------------------------------------------- customers & money

    /** Every customer who has arrived so far, in arrival order. */
    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    /**
     * Records a payment: adds the bill to revenue and records the customer's
     * satisfaction at the moment they pay (brief section 3.5).
     */
    public void recordPayment(Customer c, int cents) {
        revenueCents += cents;
        paidCount++;
        satisfactionSum += c.getSatisfaction();
    }

    public int getRevenueCents() {
        return revenueCents;
    }

    public int getPaidCount() {
        return paidCount;
    }

    /** Average satisfaction of paid customers; empty (shown as N/A) if nobody has paid. */
    public OptionalDouble averageSatisfaction() {
        return paidCount == 0 ? OptionalDouble.empty() : OptionalDouble.of((double) satisfactionSum / paidCount);
    }

    /** Customers who have been served (eating, ready to pay, or paid). */
    public int getServedCount() {
        int n = 0;
        for (Customer c : customers) {
            CustomerState s = c.getState();
            if (s == CustomerState.EATING || s == CustomerState.READY_TO_PAY || s == CustomerState.PAID) {
                n++;
            }
        }
        return n;
    }

    /** Customers who left without paying because satisfaction reached 0. */
    public int getUnhappyDepartures() {
        int n = 0;
        for (Customer c : customers) {
            if (c.getState() == CustomerState.LEFT_UNHAPPY) {
                n++;
            }
        }
        return n;
    }
}
