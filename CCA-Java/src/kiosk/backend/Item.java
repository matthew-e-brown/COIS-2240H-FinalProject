package kiosk.backend;

public class Item {
    private String name; // attribute to hold the presented name of the item object
    private float price; // attribute to hold the price of the a single item, not governed by the quantity
    private int quantity; // attribute to hold the quantity of items in the order

    /* Constructor with Quantity */
    public Item(String name, float price, int quantity) {
        // set all the attributes using arguments from the Constructor
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /* Constructor without Quantity */
    public Item(String name, float price) {
        // set attributes using arguments in the Constructor and set attribute quantity to default value of 1
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
