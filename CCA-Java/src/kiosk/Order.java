package kiosk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kiosk.backend.Item;

/** <code>Order</code> is the class which handles the user's order of food and drink items.
 *
 * An <code>Order</code> object contains a list of the food and drink items currently in the user's order.
 * Food and drink items can be added to the order, removed from the order, or the order can be reset.
 *
 */
public class Order {
    /**
     * The list of food and drink items currently in the user's order. An <code>Observable List</code> of
     * <code>Item</code> objects.
     */
    private ObservableList<Item> items;

    /** Gets the <code>items</code> field.
     *
     * @return ObservableList&lt;Item&gt; The list of food and drink items currently in the user's order.
     */
    ObservableList<Item> getItems() { return this.items; }

    /**
     * Constructs an empty <code>observableArrayList</code> of <code>Item</code> objects.
     * An instance of <code>Order</code> is empty until the user adds an item to their order.
     */
    Order() { this.items = FXCollections.observableArrayList(); }

    /**
     * Resets the user's order, by clearing all <code>Item</code> objects from the <code>items</code> field.
     */
    public void resetOrder() { this.items = FXCollections.observableArrayList(); }

    /**
     * Adds food / drink item to the user's order. If the
     * user has already ordered at least one item with the given name,
     * the quantity is increased by 1. If the user has not ordered
     * the item yet, a new <code>Item</code> object is created,
     * and is added to the <code>items</code> list.
     * For example, <code>addToOrder("Little Leaf Meal", 3.69F)</code> would
     * increase the quantity of "Little Leaf Meal"s in the user's
     * order if the quantity before method invocation was at least one.
     * Otherwise, a new <code>Item</code> object would be created,
     * and this object would be added to <code>items</code>.
     * @param name The name of the food or drink item to be added to the order.
     * @param price The price of the food or drink item to be added to the order.
     *
     */
    public void addToOrder(String name, float price) {
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
                item.setQuantity(item.getQuantity() + 1);
                return; //leave the function, you're finished
            }
        }
        this.items.add(new Item(name, price));
    }

    /**
     * Calculates the cost of the user's order before tax (the subtotal).
     * @return float The subtotal of the user's order, before taxes are added.
     */
    public float calculateSubtotal() {
        float total = 0;
        for (Item item : this.items) { total += item.getPrice() * item.getQuantity(); }
        return total;
    }
  
    int getLength() {
        int total = 0;
        for (Item item : this.items) {
            total += item.getQuantity();
        }
        return total;
    }
}