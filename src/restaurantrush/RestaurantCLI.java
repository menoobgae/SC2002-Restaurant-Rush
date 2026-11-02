package restaurantrush;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/** Owner: P2. Keyboard and console version of ManagerInput. No game rules here. */
public class RestaurantCLI implements ManagerInput {
    private final Scanner in = new Scanner(System.in);
    private final PrintStream out = System.out;

    public StaffAction chooseAction(Staff s, List<StaffAction> opts, GameSnapshot snap) {
        // TODO (P2): print the numbered options (describe()), read a number, 0 = Wait (return null)
        throw new UnsupportedOperationException("TODO: P2");
    }

    public boolean confirmHappyHour(GameSnapshot snap) {
        return false; // Stage 3 (P2): ask y/n
    }

    public void showTurnStart(GameSnapshot snap) {
        // TODO (P2): turn, revenue, avg satisfaction (N/A if empty), queue, tables, satisfaction, order status
    }

    public void showTurnReport(TurnReport r) {
        // TODO (P2): served, unhappy departures, revenue, average satisfaction, turns used
    }

    public void showOutcome(GameOutcome o) {
        // TODO (P2)
    }

    public void showMessage(String msg) {
        out.println(msg);
    }
}
