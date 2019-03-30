package kiosk;

public class Item {
    private String name;
    private float price;
    private int quantity;

    public Item() {
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Item(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // Need to be able to change quantity of items
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
