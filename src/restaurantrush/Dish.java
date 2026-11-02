package restaurantrush;

/** Owner: P2. A single menu item: Salad, Burger or Pasta. */
public class Dish implements MenuItem {
    private final String name;
    private final int priceCents;
    private final int prepUnits;

    public Dish(String name, int priceCents, int prepUnits) {
        this.name = name;
        this.priceCents = priceCents;
        this.prepUnits = prepUnits;
    }

    public String getName() { return name; }
    public int getPriceCents() { return priceCents; }
    public int getPrepUnits() { return prepUnits; }
}
