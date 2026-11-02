package restaurantrush;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> items; // List<MenuItem> instead of List<Dish> add future meals like ComboMeal

    public Menu() {
        items = new ArrayList<>();
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
