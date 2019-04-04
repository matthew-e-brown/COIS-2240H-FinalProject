package kiosk;

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
    @FXML TableView orderTable;
    @FXML Button hamburger;

    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
        hamburger.setOnAction(event -> openSideMenu());
    }

    void refreshTable() {
        orderTable = generateTable();
        System.out.println(orderTable);
    }

    private TableView<Item> generateTable() {
        TableView<Item> table = new TableView<>();

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

        /* Add columns (don't use addAll to avoid issue with unchecked generics) */
        table.getColumns().add(nameColumn);
        table.getColumns().add(priceColumn);
        table.getColumns().add(quantityColumn);

        System.out.println(table);
        return table;
    }

    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
