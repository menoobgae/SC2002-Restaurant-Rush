package restaurantrush;

/** Owner: P3. Loses 8 satisfaction per waiting turn, pays full price. */
public class RegularCustomer extends Customer {
    public RegularCustomer(int id, int arrivalTurn) {
        super(id, arrivalTurn);
    }

    @Override
    public int waitLoss() {
        return 8;
    }
}
