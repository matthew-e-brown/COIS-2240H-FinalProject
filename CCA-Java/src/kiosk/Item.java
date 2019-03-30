package kiosk;

class Item {
    private String name;
    private float price;
    private int quantity;

    /* Constructor with Quantity */
    Item(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /* Constructor without Quantity */
    Item(String name, float price) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    String getName() { return this.name; }

    float getPrice() { return this.price; }

    int getQuantity() { return this.quantity; }

    void incrementQuantity(int amount) { this.quantity += amount; }

    void decrementQuantity(int amount) { this.quantity -= amount; }

    void setQuantity(int quantity) { this.quantity = quantity; }
}
