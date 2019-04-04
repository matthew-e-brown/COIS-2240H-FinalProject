package kiosk.backend;

/**
 * Objects of the <code>Item</code> class are food or drink items
 * in the user's order.
 */
public class Item {
    /**
     * The name of the food or drink item in the user's order.
     */
    private String name;
    /**
     * The price of the food or drink item in the user's order.
     */
    private float price;
    /**
     * The quantity of the food or drink item in the user's order.
     */
    private int quantity;

    /**
     * Constructor which creates a food or drink <code>Item</code> with a default quantity of 1.
     * This constructor is called when the user adds a new food or drink item to their order.
     *
     * @param name  The name of the food or drink item.
     * @param price The price of the food or drink item.
     */
    public Item(String name, float price) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    /**
     * Gets the name of the food or drink item.
     *
     * @return String The name of the food or drink item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the price of the food or drink item.
     *
     * @return float The price of the food or drink item.
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Gets the quantity of the food or drink item in the user's order.
     *
     * @return int The number of items in the user's order with name <code>this.name</code>.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Increases the quantity by a specified <code>amount</code> in the user's order
     * for the food or drink item with name <code>this.name</code>.
     * This method is called when the user presses "add to order" more than once
     * for the same food or drink item, or when the user presses the "+" button
     * on the View Order page.
     *
     * @param amount The amount by which the quantity should be increased by.
     */
    public void incrementQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Decreases the quantity by a specified <code>amount</code> in the user's order
     * for the food or drink item with name <code>this.name</code>.
     * This method is called when the user presses the "-" button on the View Order page.
     *
     * @param amount The amount by which the quantity should be decreased by.
     */
    public void decrementQuantity(int amount) {
        this.quantity -= amount;
    }
}

