package restaurantrush;

import java.util.ArrayList;
import java.util.List;

/** Owner: P4. */
public class Waiter extends Staff {
    public Waiter() {
        super("Waiter");
    }

    @Override
    public List<StaffAction> availableActions(Restaurant r, int turn) {
        List<StaffAction> options = new ArrayList<>();
        // TODO (P4): build the possible actions from r's queue, tables and customers
        return options;
    }
}
