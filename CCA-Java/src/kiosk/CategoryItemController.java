package kiosk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CategoryItemController {
    public static final int IMAGE_WIDTH = (int)(HomeItemController.IMAGE_WIDTH * 0.8);
    public static final int IMAGE_HEIGHT = (int)(HomeItemController.IMAGE_HEIGHT * 0.8);

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
