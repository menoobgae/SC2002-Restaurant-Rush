package restaurantrush;

import java.util.ArrayList;
import java.util.List;

/** Owner: P2. The list of items customers can choose from. */
public class Menu {
    private final List<MenuItem> items;

    public Menu(List<MenuItem> items) {
        this.items = new ArrayList<>(items);
    }

    public List<MenuItem> getItems() {
        // TODO (P2): return a read-only view
        throw new UnsupportedOperationException("TODO: P2");
    }

    public MenuItem find(String name) {
        // TODO (P2): return the item with this name, or null
        throw new UnsupportedOperationException("TODO: P2");
    }
}
