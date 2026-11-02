package restaurantrush;

/**
 * One table in the restaurant. A table stays occupied until its customer
 * pays or leaves (brief section 3.4).
 */
public class Table {
    private final int id;
    private Customer occupant;

    public Table(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Customer getOccupant() {
        return occupant;
    }

    public boolean isFree() {
        return occupant == null;
    }

    /** Seats a customer. Callers must check isFree() first. */
    public void assign(Customer c) {
        if (!isFree()) {
            throw new IllegalStateException("Table " + id + " is already occupied");
        }
        if (c == null) {
            throw new IllegalArgumentException("Cannot seat a null customer");
        }
        occupant = c;
    }

    /** Frees the table after the customer pays or leaves. */
    public void release() {
        occupant = null;
    }

    @Override
    public String toString() {
        return "Table " + id;
    }
}
