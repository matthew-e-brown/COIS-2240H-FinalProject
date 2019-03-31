package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML AnchorPane root;
    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Create the table */
        TableView<Item> table;
        /* This block will be removed, just for testing */
        Order order = new Order();
        order.addToOrder("greasySticks", 3.69F);
        order.addToOrder("vanilla cone", 2.0F);
        order.addToOrder("greasySticks", 3.69F);

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

        table = new TableView<>();
        table.setItems(order.getItems());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        root.getChildren().add(new VBox(table));
    }

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
