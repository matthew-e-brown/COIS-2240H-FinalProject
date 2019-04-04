package kiosk.loadIns;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import kiosk.CategoryController;
import kiosk.HomeController;
import kiosk.backend.Menu;

import static kiosk.Main.order;

public class CategoryItemController {
    public static final int IMAGE_WIDTH = (int)(HomeItemController.IMAGE_WIDTH * 0.8);
    public static final int IMAGE_HEIGHT = (int)(HomeItemController.IMAGE_HEIGHT * 1.2);

    @FXML public StackPane root;
    @FXML public ImageView image;
    @FXML public Button button;

    public String itemName;
    private CategoryController parentController;

    public CategoryItemController(String itemName, CategoryController parent) {
        this.itemName = itemName;
        this.parentController = parent;
    }

    public void setImageURL(String URL) {
        Image img = new Image("file:src" + URL);
        this.image.setImage(img);
    }

    @FXML
    public void addToOrder() {
        order.addToOrder(itemName, Menu.getPrice(this.itemName));
        parentController.incrementOrderIcon();
    }
}
