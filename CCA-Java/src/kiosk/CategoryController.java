package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kiosk.backend.Menu;
import kiosk.loadIns.CategoryItemController;

import java.net.URL;
import java.util.ResourceBundle;

import static kiosk.Main.main;
import static kiosk.Main.order;

public class CategoryController implements Initializable {
    @FXML AnchorPane root;
    @FXML Button hamburger;
    @FXML Button orderButton;
    @FXML GridPane typesGrid;
    @FXML Pane numberPane;
    @FXML Text orderNumber;

    private String category;
    static AnchorPane sideBar;

    CategoryController(String category) { this.category = category; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typesGrid.setVgap(15);
        typesGrid.setHgap(15);

        try {
            if (this.category == null) throw new Exception("Type must be set before initialize is run");
            int i = 0, j = 0;
            for (String item : Menu.getItemsByType(this.category)) {
                // Load the category item
                CategoryItemController controller = new CategoryItemController(item, this);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("loadIns/categoryItem.fxml"));
                loader.setController(controller);
                StackPane itemPane = loader.load();

                controller.button.setText(controller.itemName);
                controller.button.setOnAction((event) -> controller.addToOrder());

                //Put a image URL
                String path = Menu.getFilepath(item);
                controller.setImageURL(path);

                typesGrid.add(itemPane, i, j);
                if (++i > 1) {
                    i = 0;
                    j++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        hamburger.setOnAction((event) -> openSideMenu());
        //Make a circle for the number;
        double width = numberPane.getPrefWidth();
        numberPane.setPrefHeight(width);
        numberPane.setClip(new Circle(width / 2, width / 2, width / 2));

        int orderLength = order.getLength();
        if (orderLength > 0) {
            numberPane.setVisible(true);
            orderNumber.setText(String.valueOf(orderLength));
        }

        orderButton.setOnAction(event -> viewOrder());
    }

    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }

    public void incrementOrderIcon() {
        if (!numberPane.isVisible()) numberPane.setVisible(true); //Show it if it doesn't exist
        int n = Integer.parseInt(this.orderNumber.getText());
        orderNumber.setText(String.valueOf(++n));
    }

    private void viewOrder() {
        Stage primaryStage = (Stage)root.getScene().getWindow();
        Main.getOrderController().refreshTable();
        primaryStage.setScene(Main.getOrderScreen());
    }
}
