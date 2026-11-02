package restaurantrush;

import java.util.OptionalDouble;

/**
 * Immutable end-of-turn results (brief section 3.5): served count, unhappy
 * departures, revenue, average satisfaction of paid customers and turns used.
 */
public final class TurnReport {
    private final int turn;
    private final int servedCount;
    private final int unhappyDepartures;
    private final int revenueCents;
    private final OptionalDouble avgSatisfaction;

    public TurnReport(int turn, int servedCount, int unhappyDepartures,
                      int revenueCents, OptionalDouble avgSatisfaction) {
        this.turn = turn;
        this.servedCount = servedCount;
        this.unhappyDepartures = unhappyDepartures;
        this.revenueCents = revenueCents;
        this.avgSatisfaction = avgSatisfaction;
    }

    public int getTurn() { return turn; }
    public int getServedCount() { return servedCount; }
    public int getUnhappyDepartures() { return unhappyDepartures; }
    public int getRevenueCents() { return revenueCents; }

    /** Empty when nobody has paid yet; the CLI shows that as "N/A". */
    public OptionalDouble getAvgSatisfaction() { return avgSatisfaction; }
}
