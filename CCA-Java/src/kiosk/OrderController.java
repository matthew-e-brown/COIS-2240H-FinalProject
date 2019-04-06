package kiosk;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kiosk.backend.Item;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import static kiosk.HomeController.BANNER_HEIGHT;
import static kiosk.HomeController.OBJECT_SIDE_OFFSET;
import static kiosk.Main.order;

/**
 * The OrderController Class controls the order screen. It is associated with the
 * <code>order.fxml</code> file.
 */
public class OrderController implements Initializable {
    /**
     * The height of the table which displays the items currently in the user's order.
     */
    public static final int TABLE_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.55);
    /**
     * The Y-position of the "subtotal" text field on the order screen.
     */
    public static final int SUBTOTAL_POS = BANNER_HEIGHT + TABLE_HEIGHT + 2 * OBJECT_SIDE_OFFSET;
    /**
     * The Y-position of the "hst" text field on the order screen.
     */
    public static final int HST_POS = SUBTOTAL_POS + OBJECT_SIDE_OFFSET;
    /**
     * The Y-position of the "total" text field on the order screen.
     */
    public static final int TOTAL_POS = HST_POS + OBJECT_SIDE_OFFSET;
    /**
     * The width of the confirmation popup.
     */
    public static final int POPUP_WIDTH = (int)(Main.HALF_WIDTH * 1.35);
    /**
     * The height of the confirmation popup.
     */
    public static final int POPUP_HEIGHT = (int)(Main.QUARTER_HEIGHT * 1.35);
    /**
     * The distance that the confirmation popup is from the sides of the window.
     */
    public static final int ANCHOR_SIDE_DISTANCE = Main.HALF_WIDTH - POPUP_WIDTH / 2;
    /**
     * The distance that the top and bottom of the confirmation popup are from the top and
     * bottom respectively of the window. The confirmation popup is centered.
      */
    public static final int ANCHOR_TOP_BOTTOM_DISTANCE = Main.HEIGHT / 2 - POPUP_HEIGHT / 2;

    /**
     * The AnchorPane for the order screen in which all of the other nodes are contained.
     */
    @FXML AnchorPane root;
    /**
     * The table which contains the items currently in the user's order.
     */
    @FXML public TableView<Item> orderTable;
    /**
     * The button which allows the user to open the navigation drawer.
     */
    @FXML Button hamburger;
    /**
     * The button which allows the user to send their order to the kitchen.
     */
    @FXML Button submitOrder;
    /**
     * The button which allows the user to reset their order. Pushing this button
     * clears the order table.
     */
    @FXML Button resetOrder;
    /**
     * The pane which holds text telling the user their order number, informs them
     * that their request was shipped off to the kitchen, and offers a button to
     * reset the program back to the Splash Screen.
     */
    @FXML AnchorPane confirmation;
    /**
     * A reference to the <code>Text</code> object that holds the "Order #....." text.
     */
    @FXML Text orderNumber;
    /**
     * A reference to the button that resets the program when the user is complete and
     * has been shown their order number.
     */
    @FXML Button continueButton;

    /**
     * The TextField which displays the amount of HST (Harmonized Sales Tax) for the user's order.
     */
    @FXML TextField hst;
    /**
     * The TextField which displays the subtotal (before tax) for the user's order.
     */
    @FXML TextField subtotal;
    /**
     * The TextField which displays the total (including tax) for the user's order.
     */
    @FXML TextField total;

    /**
     * The AnchorPane which contains the sidebar / navigation drawer.
     */
    static AnchorPane sideBar;

    /**
     * Initializes the order screen.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // When the order screen is opened, display the order table
        generateTable(orderTable);
        hamburger.setOnAction(event -> openSideMenu());
        resetOrder.setOnAction(event -> clearOrder());
        submitOrder.setOnAction(event -> displayConfirmation());
        continueButton.setOnAction(event -> {
            clearOrder();
            confirmation.setVisible(false);
            confirmation.setManaged(false);
            Main.resetKiosk((Stage)root.getScene().getWindow());
        });
    }

    /**
     * Generates the table which displays the items currently in the user's order.
     * @param table A TableView of Items to be filled with data from the <code>oder.getItems()</code> method.
     */
    private void generateTable(TableView<Item> table) {
        // Create a list of the Items currently in the user's order
        ObservableList<Item> itemList = order.getItems();
        itemList.addListener((ListChangeListener<Item>) c -> refreshLabels());
        table.setPlaceholder(new Label("Add some items to your order to see them here!"));

        //Name column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //Price column
        TableColumn<Item, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(50);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("PriceString"));

        //Quantity column
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(50);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        //Increase Quantity column
        TableColumn<Item, String> increaseColumn = new TableColumn<>("Add");
        increaseColumn.setMinWidth(20);
        increaseColumn.setCellValueFactory(new PropertyValueFactory<>("IncreaseQuantityButton"));

        //Decrease Quantity column
        TableColumn<Item, String> decreaseColumn = new TableColumn<>("Remove");
        decreaseColumn.setMinWidth(20);
        decreaseColumn.setCellValueFactory(new PropertyValueFactory<>("DecreaseQuantityButton"));

        // Set the items in the table to the items currently in the user's order
        table.setItems(itemList);

        // Set the column widths of the table
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(44f/100f)); //45%
        priceColumn.prefWidthProperty().bind(table.widthProperty().multiply(20f/100f)); //20%
        quantityColumn.prefWidthProperty().bind(table.widthProperty().multiply(15f/100f)); //15%
        increaseColumn.prefWidthProperty().bind(table.widthProperty().multiply(10f/100f)); //10%
        decreaseColumn.prefWidthProperty().bind(table.widthProperty().multiply(10f/100f)); //10%

        nameColumn.setId("name-column");
        priceColumn.setId("price-column");
        quantityColumn.setId("quantity-column");
        increaseColumn.setId("increase-column");
        decreaseColumn.setId("decrease-column");

        table.setSelectionModel(null);
        for (TableColumn col : new TableColumn[] { nameColumn, priceColumn, quantityColumn, increaseColumn, decreaseColumn }) {
            // Prevent the user from being able to sort or resize the columns
            col.setSortable(false);
            col.setResizable(false);
            table.getColumns().add(col);
        }
    }

    /**
     * Refreshes the subtotal, HST, and total for the user's order, and redraws the text on-screen.
     */
    public void refreshLabels() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        float sub = order.calculateSubtotal();
        float hst = sub * 0.130003F;
        float tot = sub + hst;
        this.subtotal.setText(currencyFormat.format(sub));
        this.hst.setText(currencyFormat.format(hst));
        this.total.setText(currencyFormat.format(tot));
    }

    /**
     * Opens the side bar / navigation drawer. This method is called when the user
     * pushes the hamburger button.
     */
    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }

    /**
     * Generates an order number for the user, then shows the pane holding the order number and the
     * button which allows them to end their session.
     */
    private void displayConfirmation() {
        if (order.getLength() > 0) {
            orderNumber.setText("Order #" + Math.abs(order.getItems().hashCode()));
            confirmation.setManaged(true);
            confirmation.setVisible(true);
        }
    }

    /**
     * Clears the items in the user's order, then refreshes the table and text-fields for prices.
     */
    private void clearOrder() {
        //Clear all items in order, then clear order table and refresh it
        order.resetOrder();

        subtotal.setText("");
        hst.setText("");
        total.setText("");

        orderTable.getColumns().clear();
        generateTable(orderTable);
    }
}
