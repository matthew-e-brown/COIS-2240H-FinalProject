package kiosk;

import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    /* Dimensions for VBox */
    public static final int PREV_HEIGHT = 170; //Preview Height
    public static final int WIDTH = Main.HALF_WIDTH; //Width of the Side-Panel
    public static final int HEIGHT = Main.HEIGHT - PREV_HEIGHT; //Height of the VBox

    @FXML AnchorPane root;
    static public VBox dick;
    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getChildren().add(dick);
    }

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
