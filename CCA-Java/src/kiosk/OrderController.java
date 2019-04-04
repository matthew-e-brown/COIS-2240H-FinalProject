package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
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
    @FXML TableView<Item> orderTable;
    @FXML Button hamburger;

    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable(orderTable);
        hamburger.setOnAction(event -> openSideMenu());
    }

    private void refreshTable(TableView<Item> table) {
        //Name column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //Price column
        TableColumn<Item, Float> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //Quantity column
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        /* Create the table */
        table.setItems(order.getItems());

        nameColumn.setMaxWidth(1F * Integer.MAX_VALUE * 70);
        priceColumn.setMaxWidth(1F * Integer.MAX_VALUE * 15);
        quantityColumn.setMaxWidth(1F * Integer.MAX_VALUE * 15);

        /* Add columns (don't use addAll to avoid issue with unchecked generics) */
        table.getColumns().add(nameColumn);
        table.getColumns().add(priceColumn);
        table.getColumns().add(quantityColumn);
    }

    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
