package kiosk;

public class Item {
    private String name;
    private float price;
    private int quantity;

    /* Constructor with Quantity */
    public Item(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /* Constructor without Quantity */
    public Item(String name, float price) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public String getName() { return this.name; }

    public float getPrice() { return this.price; }

    public int getQuantity() { return this.quantity; }

    public void incrementQuantity(int amount) { this.quantity += amount; }

    public void decrementQuantity(int amount) { this.quantity -= amount; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
