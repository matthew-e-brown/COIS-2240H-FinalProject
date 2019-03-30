package kiosk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class Order {
    private ObservableList<Item> items;
    public ObservableList<Item> getItems() { return this.items; }

    Order() {
        this.items = FXCollections.observableArrayList();
    }

    void resetOrder() {
        this.items = FXCollections.observableArrayList();
    }

    void addToOrder(String name, float price) {
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
                item.incrementQuantity(1);
                return; //leave the function, you're finished
            }
        }
        this.items.add(new Item(name, price));
    }

    void removeFromOrder(String name) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                this.items.remove(i);
            }
        }
    }

    float calculateSubtotal() {
        float total = 0;
        for (Item item : this.items) { total += item.getPrice() * item.getQuantity(); }
        return total;
    }
}