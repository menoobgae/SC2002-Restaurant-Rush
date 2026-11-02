package restaurantrush;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Owner: P4. Takes at most one payment per turn, automatically (no manager choice). */
public class Cashier extends Staff {
    public Cashier() {
        super("Cashier");
    }

    @Override
    public List<StaffAction> availableActions(Restaurant r, int turn) {
        return Collections.emptyList(); // the Cashier is automatic
    }

    /**
     * Oldest READY_TO_PAY customer who became ready on an earlier turn pays:
     * onPaid hook, r.recordPayment(c, order price), order.markPaid(),
     * c.markPaid(), free their table. Returns who paid, if anyone.
     */
    public Optional<Customer> collectPayment(Restaurant r, int turn) {
        // TODO (P4)
        return Optional.empty();
    }
}
