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

import java.net.URL;
import java.util.ResourceBundle;

import static kiosk.Main.order;

public class OrderController implements Initializable {
    @FXML AnchorPane root;
    @FXML public TableView<Item> orderTable;
    @FXML Button hamburger;

    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable(orderTable);
        hamburger.setOnAction(event -> openSideMenu());
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
        TableColumn<Item, String> increaseQuantityColumn = new TableColumn<>("Increase Quantity");
        increaseQuantityColumn.setMinWidth(20);
        increaseQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("IncreaseQuantityButton"));

        //Decrease Quantity column
        TableColumn<Item, String> decreaseQuantityColumn = new TableColumn<>("Decrease Quantity");
        decreaseQuantityColumn.setMinWidth(20);
        decreaseQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("DecreaseQuantityButton"));

        /* Create the table */
        table.setItems(order.getItems());

        nameColumn.setMaxWidth(1F * Integer.MAX_VALUE * 70);
        priceColumn.setMaxWidth(1F * Integer.MAX_VALUE * 15);
        quantityColumn.setMaxWidth(1F * Integer.MAX_VALUE * 15);
        increaseQuantityColumn.setMaxWidth(1F * Integer.MAX_VALUE * 15);
        decreaseQuantityColumn.setMaxWidth(1F * Integer.MAX_VALUE * 15);

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
}
