package restaurantrush;

/** Owner: P4. One staff task the manager can pick. */
public interface StaffAction {
    String describe();
    ValidationResult validate(Restaurant r, int turn);
    void execute(Restaurant r, int turn);
}
