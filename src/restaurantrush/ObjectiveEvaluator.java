package restaurantrush;

import java.util.OptionalDouble;

/**
 * Judges the game after the last turn (brief section 2.1): Victory only if
 * at least 4 customers paid AND revenue is at least $45.00 AND the average
 * satisfaction of paid customers is at least 60.
 */
public class ObjectiveEvaluator {
    private static final int MIN_PAID = 4;
    private static final int MIN_REVENUE_CENTS = 4500;
    private static final int MIN_SATISFACTION = 60;

    public GameOutcome evaluate(Restaurant r) {
        OptionalDouble avg = r.averageSatisfaction();
        boolean enoughPaid = r.getPaidCount() >= MIN_PAID;
        boolean enoughRevenue = r.getRevenueCents() >= MIN_REVENUE_CENTS;
        boolean happyEnough = avg.isPresent() && avg.getAsDouble() >= MIN_SATISFACTION;
        return (enoughPaid && enoughRevenue && happyEnough) ? GameOutcome.VICTORY : GameOutcome.DEFEAT;
    }
}
