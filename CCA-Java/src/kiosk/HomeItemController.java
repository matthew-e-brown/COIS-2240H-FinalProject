package kiosk;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HomeItemController {
    public static final int IMAGE_WIDTH = 250;
    public static final int IMAGE_HEIGHT = 160;

    @FXML StackPane root;
    @FXML ImageView image;
    @FXML Button button;

    void setImageURL(String URL) {
        Image img = new Image("file:src" + URL);
        this.image.setImage(img);
    }
}
