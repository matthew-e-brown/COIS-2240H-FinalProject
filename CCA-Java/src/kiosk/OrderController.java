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

public class OrderController implements Initializable {
    public static final int TABLE_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.55);
    public static final int SUBTOTAL_POS = BANNER_HEIGHT + TABLE_HEIGHT + 2 * OBJECT_SIDE_OFFSET;
    public static final int HST_POS = SUBTOTAL_POS + OBJECT_SIDE_OFFSET;
    public static final int TOTAL_POS = HST_POS + OBJECT_SIDE_OFFSET;

    public static final int POPUP_WIDTH = (int)(Main.HALF_WIDTH * 1.35);
    public static final int POPUP_HEIGHT = (int)(Main.QUARTER_HEIGHT * 1.35);
    public static final int ANCHOR_SIDE_DISTANCE = Main.HALF_WIDTH - POPUP_WIDTH / 2;
    public static final int ANCHOR_TOP_BOTTOM_DISTANCE = Main.HEIGHT / 2 - POPUP_HEIGHT / 2;

    @FXML AnchorPane root;
    @FXML public TableView<Item> orderTable;
    @FXML Button hamburger;
    @FXML Button submitOrder;
    @FXML Button resetOrder;
    @FXML AnchorPane confirmation;
    @FXML Text orderNumber;
    @FXML Button continueButton;

    @FXML TextField hst;
    @FXML TextField subtotal;
    @FXML TextField total;

    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    private void generateTable(TableView<Item> table) {
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

        table.setItems(itemList);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(45f/100f)); //45%
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
            col.setSortable(false);
            col.setResizable(false);
            table.getColumns().add(col);
        }
    }

    public void refreshLabels() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        float sub = order.calculateSubtotal();
        float hst = sub * 0.130003F;
        float tot = sub + hst;
        this.subtotal.setText(currencyFormat.format(sub));
        this.hst.setText(currencyFormat.format(hst));
        this.total.setText(currencyFormat.format(tot));
    }

    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }

    private void displayConfirmation() {
        if (order.getLength() > 0) {
            orderNumber.setText("Order #" + Math.abs(order.getItems().hashCode()));
            confirmation.setManaged(true);
            confirmation.setVisible(true);
        }
    }

    private void clearOrder() {
        //Clear all items in order, then clear order table and refresh it
        order.resetOrder();
        orderTable.getColumns().clear();
        generateTable(orderTable);
    }
}
