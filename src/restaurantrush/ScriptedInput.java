package restaurantrush;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/** Owner: P2. Replays saved choices so the victory/defeat runs are reproducible. */
public class ScriptedInput implements ManagerInput {
    private final Queue<String> script = new ArrayDeque<>();

    public ScriptedInput(String file) {
        // TODO (P2): read the file's lines into script
    }

    public StaffAction chooseAction(Staff s, List<StaffAction> opts, GameSnapshot snap) {
        // TODO (P2): take the next line (an option number; 0 = Wait); return null when the script runs out
        throw new UnsupportedOperationException("TODO: P2");
    }

    public boolean confirmHappyHour(GameSnapshot snap) { return false; }
    public void showTurnStart(GameSnapshot snap) { }
    public void showTurnReport(TurnReport r) { }
    public void showOutcome(GameOutcome o) { System.out.println("Result: " + o); }
    public void showMessage(String msg) { System.out.println(msg); }
}
