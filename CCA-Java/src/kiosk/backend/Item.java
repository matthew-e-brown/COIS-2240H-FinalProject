package kiosk.backend;

import javafx.scene.control.Button;
import kiosk.Main;
import kiosk.OrderController;

import java.text.NumberFormat;

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
     * The button that is visible in the table on the order screen, which allows user to increase quantity  of
     * this food or drink item in their order by 1.
     */
    private Button increaseQuantityButton;

    /**
     * The button that is visible in the table on the order screen, which allows user to decrease quantity of
     * this food or drink item in their order by 1.
     */
    private Button decreaseQuantityButton;

    /**
     * Constructor which creates a food or drink <code>Item</code> with a default quantity of 1.
     * This constructor is called when the user adds a new food or drink item to their order.
     *
     * @param name  The name of the food or drink item.
     * @param price The price of the food or drink item.
     */
    public Item(String name, float price) {
        // set attributes using arguments in the Constructor and set attribute quantity to default value of 1
        this.name = name;
        this.price = price;
        this.quantity = 1;
        this.increaseQuantityButton = new Button("+");
        this.decreaseQuantityButton = new Button("-");
        this.increaseQuantityButton.setOnAction(event -> {
            this.quantity++;
            Main.getOrderController().orderTable.refresh();
        });
        this.decreaseQuantityButton.setOnAction(event -> {
            if (this.quantity > 1) this.quantity--;
            else Main.getOrderController().orderTable.getItems().remove(this);
            Main.getOrderController().orderTable.refresh();
        });
    }

    /**
     * Gets the name of the food or drink item.
     *
     * @return String The name of the food or drink item.
     */
    public String getName() { return this.name; }
  
    /**
     * Gets the price of the food or drink item.
     *
     * @return String The price of the food or drink item, formatted as local currency.
     */
    public String getPriceString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(this.price);
    }

    public float getPrice() { return this.price; }

    /**
     * Gets the quantity of the food or drink item in the user's order.
     *
     * @return int The number of items in the user's order with name <code>this.name</code>.
     */
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Button getIncreaseQuantityButton() { return this.increaseQuantityButton; }
    public Button getDecreaseQuantityButton() { return this.decreaseQuantityButton; }
}

