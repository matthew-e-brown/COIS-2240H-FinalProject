package kiosk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

class Order {
    private ArrayList<String> names;
    private ObservableList<Item> items;

    public Order() {
        this.names = new ArrayList<String>();
        this.items = FXCollections.observableArrayList();
    }

    public ObservableList<Item> getItems() {
        return this.items;
    }

    public void addToOrder(String name, float price) {
        // If the item is already in the order, just increase the quantity; otherwise, add it to order
        if (this.names.contains(name)) {
            int index = getNameIndex(name);
            int newQuantity = items.get(index).getQuantity() + 1;
            items.get(index).setQuantity(newQuantity);
        } else {
            this.names.add(name);
            this.items.add(new Item(name, price, 1));
        }
    }

    public void removeFromOrder(String name) {
        int index = getNameIndex(name);
        this.names.remove(index);
        this.items.remove(index);
    }

    public void resetOrder() {
        this.names = new ArrayList<String>();
        this.items = FXCollections.observableArrayList();
    }

    public float calculateSubtotal() {
        float total = 0;
        for (int i = 0; i < this.items.size(); i++) {
            total += this.items.get(i).getPrice() * this.items.get(i).getQuantity();
        }
        return total;
    }

    private int getNameIndex(String name) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}