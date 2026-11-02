package restaurantrush;

/** Owner: P4. */
public class ServeOrderAction implements StaffAction {
    private final Order order;

    public ServeOrderAction(Order order) {
        this.order = order;
    }

    public String describe() {
        // TODO (P4): short text shown in the menu of choices
        throw new UnsupportedOperationException("TODO: P4");
    }

    public ValidationResult validate(Restaurant r, int turn) {
        // TODO (P4): ValidationResult.ok() or ValidationResult.fail("reason")
        throw new UnsupportedOperationException("TODO: P4");
    }

    public void execute(Restaurant r, int turn) {
        // TODO (P4)
        throw new UnsupportedOperationException("TODO: P4");
    }
}
