package restaurantrush;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Runs the game: 18 turns, each with the six steps of brief section 3.2, then
 * the Victory/Defeat check. GameController only sequences the steps; each
 * rule lives in the object it belongs to (staff actions, Customer, Order,
 * Cashier, ObjectiveEvaluator).
 *
 * It talks to the player only through the ManagerInput interface, so the
 * console (RestaurantCLI) can be swapped for ScriptedInput to replay a run.
 *
 * Stage 1 version.
 */
public class GameController {
    private static final int TABLE_COUNT = 2;

    private final int maxTurns = 18;
    private int turn = 0;

    private final ManagerInput input;
    private final Restaurant restaurant;
    private final PricingService pricing;
    private final ArrivalSchedule schedule;
    private final ObjectiveEvaluator evaluator;
    private final Cashier cashier;

    public GameController(ManagerInput input) {
        this.input = input;
        this.pricing = new PricingService();
        Menu menu = new Menu(Arrays.asList(
                new Dish("Salad", 900, 1),
                new Dish("Burger", 1500, 1),
                new Dish("Pasta", 1800, 2)));
        this.restaurant = new Restaurant(TABLE_COUNT, menu, pricing);

        // Staff act in registration order: the Waiter always acts before the Chef.
        restaurant.registerStaff(new Waiter());
        restaurant.registerStaff(new Chef());
        this.cashier = new Cashier();
        restaurant.registerStaff(cashier); // offers no choices: payment is automatic

        this.schedule = new ArrivalSchedule();
        this.evaluator = new ObjectiveEvaluator();
    }

    /** Plays all 18 turns (there is no early ending) and returns the result. */
    public GameOutcome run() {
        while (turn < maxTurns) {
            TurnReport report = runTurn();
            input.showTurnReport(report);
        }
        GameOutcome outcome = evaluator.evaluate(restaurant);
        input.showOutcome(outcome);
        return outcome;
    }

    /** One turn: the six steps of brief section 3.2, in order. */
    private TurnReport runTurn() {
        turn++;
        processArrivals();                    // step 1
        input.showTurnStart(snapshot());      // start-of-turn display (section 3.1)
        managerChoices();                     // steps 2-3: Waiter acts before Chef
        eatingAndPayment();                   // step 4
        waitingAndAbandonment();              // step 5
        return new TurnReport(turn, restaurant.getServedCount(), restaurant.getUnhappyDepartures(),
                restaurant.getRevenueCents(), restaurant.averageSatisfaction());
    }

    // ------------------------------------------------------------ step 1

    private void processArrivals() {
        Optional<Customer> arrival = schedule.arrivingOn(turn);
        if (arrival.isPresent()) {
            restaurant.enqueue(arrival.get());
            input.showMessage("Customer #" + arrival.get().getId() + " arrives and joins the queue.");
        }
    }

    // ---------------------------------------------------------- steps 2-3

    /**
     * Asks each staff member, in registration order, for at most one task.
     * Because each task runs before the next staff member is asked, the Chef
     * can cook an order the Waiter took this turn, and a dish the Chef
     * finishes this turn is not yet servable (Order.isServable checks that).
     */
    private void managerChoices() {
        for (Staff s : restaurant.getStaff()) {
            askForTask(s);
        }
    }

    /**
     * Gets one task for a staff member. An invalid choice explains why and lets
     * the manager choose again without using up the task (brief section 3.4).
     * Choosing null means Wait.
     */
    private void askForTask(Staff s) {
        while (true) {
            List<StaffAction> options = s.availableActions(restaurant, turn);
            if (options.isEmpty()) {
                return; // nothing this staff member can do (the Cashier always lands here)
            }
            StaffAction choice = input.chooseAction(s, options, snapshot());
            if (choice == null) {
                input.showMessage(s.getName() + " waits.");
                return;
            }
            ValidationResult result = choice.validate(restaurant, turn);
            if (result.isValid()) {
                choice.execute(restaurant, turn);
                input.showMessage(s.getName() + ": " + choice.describe());
                return;
            }
            input.showMessage("Not allowed: " + result.getMessage() + " Choose again.");
        }
    }

    // ------------------------------------------------------------ step 4

    /**
     * A customer served on an earlier turn finishes eating and becomes
     * READY_TO_PAY. Then the Cashier handles at most one READY_TO_PAY customer,
     * oldest first, who became ready on an earlier turn.
     */
    private void eatingAndPayment() {
        for (Customer c : restaurant.getCustomers()) {
            if (c.getState() == CustomerState.EATING && c.getServedOnTurn() < turn) {
                c.finishEating(turn);
            }
        }
        Optional<Customer> paid = cashier.collectPayment(restaurant, turn);
        if (paid.isPresent()) {
            input.showMessage("Cashier: customer #" + paid.get().getId() + " paid.");
        }
    }

    // ------------------------------------------------------------ step 5

    /**
     * Every waiting or seated customer who has not been served loses
     * satisfaction (amount decided by the customer's own waitLoss()). A
     * customer who reaches 0 leaves without paying: their unfinished order is
     * cancelled and their table freed. Served customers lose nothing.
     */
    private void waitingAndAbandonment() {
        for (Customer c : new ArrayList<>(restaurant.getCustomers())) {
            CustomerState s = c.getState();
            boolean waiting = s == CustomerState.WAITING_IN_QUEUE
                    || s == CustomerState.SEATED
                    || s == CustomerState.ORDERED;
            if (!waiting) {
                continue;
            }
            c.loseSatisfaction();
            if (c.getSatisfaction() == 0) {
                depart(c);
            }
        }
    }

    private void depart(Customer c) {
        Order order = c.getOrder();
        if (order != null) {
            order.cancel();
        }
        Table table = restaurant.tableOf(c);
        if (table != null) {
            table.release();
        }
        restaurant.removeFromQueue(c);
        c.leave();
        input.showMessage("Customer #" + c.getId() + " left unhappy without paying.");
    }

    // ------------------------------------------------------------ display

    /** Read-only view of the current state for the UI. */
    public GameSnapshot snapshot() {
        return new GameSnapshot(turn, restaurant.getRevenueCents(), restaurant.averageSatisfaction(),
                restaurant.getQueue(), new ArrayList<>(restaurant.getTables()), 0);
    }
}
