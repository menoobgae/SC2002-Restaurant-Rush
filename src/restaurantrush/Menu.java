package restaurantrush;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> items; // List<MenuItem> instead of List<Dish> add future meals like ComboMeal

    // Constructor for an empty Menu
    public Menu() {
        items = new ArrayList<>();
    }

    // Constructor for a Menu with existing items
    public Menu(List<MenuItem> items) {
        this.items = new ArrayList<>(items);
    }
    
    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public MenuItem find(String name) {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(MenuItem item) {
        if (item != null) {
        items.add(item);
        }
    }
}
