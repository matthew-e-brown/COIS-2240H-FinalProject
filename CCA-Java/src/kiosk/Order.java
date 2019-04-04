package kiosk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kiosk.backend.Item;

public class Order {
    private ObservableList<Item> items;
    ObservableList<Item> getItems() { return this.items; }

    Order() { this.items = FXCollections.observableArrayList(); }

    public void resetOrder() { this.items = FXCollections.observableArrayList(); }

    public void addToOrder(String name, float price) {
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
                item.incrementQuantity(1);
                return; //leave the function, you're finished
            }
        }
        this.items.add(new Item(name, price));
    }

    public void removeFromOrder(String name) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                this.items.remove(i);
                return; //leave the function, you're finished
            }
        }
    }

    public int getLength() { return this.items.size(); }

    public float calculateSubtotal() {
        float total = 0;
        for (Item item : this.items) { total += item.getPrice() * item.getQuantity(); }
        return total;
    }
}