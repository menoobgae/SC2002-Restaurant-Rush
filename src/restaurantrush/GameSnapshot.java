package restaurantrush;

import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Read-only view of the game at the start of a turn, handed to the UI
 * (brief section 3.1). The UI reads it and never changes the game through it.
 */
public final class GameSnapshot {
    private final int turn;
    private final int revenueCents;
    private final OptionalDouble avgSatisfaction;
    private final List<Customer> queue;
    private final List<Table> tables;
    private final int happyHourRemaining;

    public GameSnapshot(int turn, int revenueCents, OptionalDouble avgSatisfaction,
                        List<Customer> queue, List<Table> tables, int happyHourRemaining) {
        this.turn = turn;
        this.revenueCents = revenueCents;
        this.avgSatisfaction = avgSatisfaction;
        this.queue = Collections.unmodifiableList(queue);
        this.tables = Collections.unmodifiableList(tables);
        this.happyHourRemaining = happyHourRemaining;
    }

    public int getTurn() { return turn; }
    public int getRevenueCents() { return revenueCents; }
    public OptionalDouble getAvgSatisfaction() { return avgSatisfaction; }

    /** Customers waiting for a table, oldest first. */
    public List<Customer> getQueue() { return queue; }

    /** All tables; a table's occupant (if any) is the seated customer. */
    public List<Table> getTables() { return tables; }

    /** Turns of Happy Hour left (always 0 before Stage 3). */
    public int getHappyHourRemaining() { return happyHourRemaining; }
}
