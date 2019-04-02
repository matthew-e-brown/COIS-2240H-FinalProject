package kiosk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CategoryItemController {
    public static final int IMAGE_WIDTH = HomeItemController.IMAGE_WIDTH;
    public static final int IMAGE_HEIGHT = HomeItemController.IMAGE_HEIGHT;

    @FXML StackPane root;
    @FXML ImageView image;
    @FXML Button button;

    String itemName;

    CategoryItemController(String itemName) { this.itemName = itemName; }

    void setImageURL(String URL) {
        Image img = new Image("file:src" + URL);
        this.image.setImage(img);
    }

    @FXML
    public void addToOrder() {
        System.out.println("Adding " + this.itemName + " to order.");
    }
}
