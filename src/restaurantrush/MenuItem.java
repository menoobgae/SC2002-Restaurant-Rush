package restaurantrush;

/** Owner: P2. Anything a customer can order (a Dish now, a ComboMeal in Stage 3). */
public interface MenuItem {
    String getName();
    int getPriceCents();
    int getPrepUnits();
}
