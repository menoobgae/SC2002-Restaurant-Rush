package restaurantrush;

import java.util.List;

/**
 * Owner: P2. Everything GameController needs from the player and the screen.
 * RestaurantCLI (keyboard) and ScriptedInput (replay file) both implement it.
 */
public interface ManagerInput {
    /** Pick one of the options for this staff member, or return null to Wait. */
    StaffAction chooseAction(Staff s, List<StaffAction> opts, GameSnapshot snap);

    /** Stage 3: does the manager switch Happy Hour on this turn? */
    boolean confirmHappyHour(GameSnapshot snap);

    void showTurnStart(GameSnapshot snap);
    void showTurnReport(TurnReport r);
    void showOutcome(GameOutcome o);
    void showMessage(String msg);
}
