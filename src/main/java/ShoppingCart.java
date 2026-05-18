import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    List<Item> items = new ArrayList<>();

    public int getItemCount() {
        return items.size();
    }

    public void addItem(String name, double price) {
        Item item = new Item(name, price);
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public int removeItem(String name) {
        int removedItem = 0;
        Iterator<Item> iterator = items.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();

            if (item.getName().equals(name)) {
                iterator.remove();
                removedItem++;
            }
        }
        return removedItem;
    }
}
