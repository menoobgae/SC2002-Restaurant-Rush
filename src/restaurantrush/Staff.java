package restaurantrush;

import java.util.List;

/** Owner: P4. A staff member. GameController asks each one for at most one task per turn. */
public abstract class Staff {
    private final String name;

    protected Staff(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    /** The tasks this staff member could do right now (empty = nothing to do). */
    public abstract List<StaffAction> availableActions(Restaurant r, int turn);
}
