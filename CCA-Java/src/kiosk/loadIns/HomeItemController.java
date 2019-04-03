package kiosk.loadIns;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kiosk.Main;

public class HomeItemController {
    public static final int IMAGE_WIDTH = 250;
    public static final int IMAGE_HEIGHT = 160;

    private String category;

    @FXML public StackPane root;
    @FXML public ImageView image;
    @FXML public Button button;

    public HomeItemController(String category) { this.category = category; }

    public void setImageURL(String URL) {
        Image img = new Image("file:src" + URL);
        this.image.setImage(img);
    }

    // Cannot be @FXML because it is referenced sans-Controller
    public void changeScene() { Main.selectCategory(this.root, this.category); }
}
