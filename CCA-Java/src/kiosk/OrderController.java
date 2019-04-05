package kiosk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kiosk.backend.Item;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static kiosk.HomeController.*;
import static kiosk.Main.*;

public class OrderController implements Initializable {
    public static final int TABLE_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.55);
    public static final int SUBTOTAL_POS = BANNER_HEIGHT + TABLE_HEIGHT + 2 * OBJECT_SIDE_OFFSET;
    public static final int HST_POS = SUBTOTAL_POS + OBJECT_SIDE_OFFSET;
    public static final int TOTAL_POS = HST_POS + OBJECT_SIDE_OFFSET;
    @FXML AnchorPane root;
    @FXML public TableView<Item> orderTable;
    @FXML Button hamburger;
    @FXML Button submitOrder;
    @FXML Button resetOrder;
   // @FXML TextField subtotal;

    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable(orderTable);
        //subtotal = new TextField();
        //subtotal.setText("hi");
        hamburger.setOnAction(event -> openSideMenu());
        resetOrder.setOnAction(event -> clearOrder());
        submitOrder.setOnAction(event -> displayConfirmation());
    }

    public void refreshTable(TableView<Item> table) {
        //Name column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //Price column
        TableColumn<Item, Float> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(50);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //Quantity column
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(50);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        //Increase Quantity column
        TableColumn<Item, String> increaseQuantityColumn = new TableColumn<>("Add");
        increaseQuantityColumn.setMinWidth(20);
        increaseQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("IncreaseQuantityButton"));

        //Decrease Quantity column
        TableColumn<Item, String> decreaseQuantityColumn = new TableColumn<>("Remove");
        decreaseQuantityColumn.setMinWidth(20);
        decreaseQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("DecreaseQuantityButton"));

        /* Create the table */
        table.setItems(order.getItems());

        table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        nameColumn.setPrefWidth(1F * Integer.MAX_VALUE * 50);
        priceColumn.setPrefWidth(1F * Integer.MAX_VALUE * 10);
        quantityColumn.setPrefWidth(1F * Integer.MAX_VALUE * 10);
        increaseQuantityColumn.setPrefWidth(1F * Integer.MAX_VALUE * 15);
        decreaseQuantityColumn.setPrefWidth(1F * Integer.MAX_VALUE * 15);
/*
        nameColumn.prefWidthProperty().bind(table.widthProperty().divide(2));
        priceColumn.prefWidthProperty().bind(table.widthProperty().divide(8));
        quantityColumn.prefWidthProperty().bind(table.widthProperty().divide(8));
        increaseQuantityColumn.prefWidthProperty().bind(table.widthProperty().divide(8));
        decreaseQuantityColumn.prefWidthProperty().bind(table.widthProperty().divide(8));
        nameColumn.setPrefWidth(tableWidth * 0.4);
        priceColumn.setPrefWidth(tableWidth * 0.15);
        quantityColumn.setPrefWidth(tableWidth * 0.15);
        increaseQuantityColumn.setPrefWidth(tableWidth * 0.15);
        decreaseQuantityColumn.setPrefWidth(tableWidth * 0.15);
        */

        /* Add columns (don't use addAll to avoid issue with unchecked generics) */
        table.getColumns().add(nameColumn);
        table.getColumns().add(priceColumn);
        table.getColumns().add(quantityColumn);
        table.getColumns().add(increaseQuantityColumn);
        table.getColumns().add(decreaseQuantityColumn);
    }

    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }

    private void displayConfirmation() { System.out.println("Order received"); }

    private void clearOrder() {
        //Clear all items in order, then clear order table and refresh it
        order.resetOrder();
        Main.getOrderController().orderTable.getColumns().clear();
        Main.getOrderController().refreshTable(Main.getOrderController().orderTable);
    }
}
