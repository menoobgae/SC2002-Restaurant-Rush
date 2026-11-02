package restaurantrush;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Decides who arrives when. Base setting (brief section 2.1): one customer at
 * the start of turns 1, 4, 7, 10, 13 and 16 - six in total.
 *
 * Stage 1: every arrival is a RegularCustomer.
 */
public class ArrivalSchedule {
    private static final int[] ARRIVAL_TURNS = {1, 4, 7, 10, 13, 16};

    private final Map<Integer, Customer> arrivals = new HashMap<>();

    public ArrivalSchedule() {
        int id = 1;
        for (int turn : ARRIVAL_TURNS) {
            arrivals.put(turn, new RegularCustomer(id++, turn));
        }
    }

    /** The customer who arrives at the start of this turn, if any. */
    public Optional<Customer> arrivingOn(int turn) {
        return Optional.ofNullable(arrivals.get(turn));
    }
}
