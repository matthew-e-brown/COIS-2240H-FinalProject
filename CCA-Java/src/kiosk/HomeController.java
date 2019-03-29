package kiosk;

import javafx.scene.layout.*;
import javafx.fxml.FXML;

public class HomeController {
    /* Dimensions for VBox */
    public static final int PREV_HEIGHT = 170; //Preview Height
    public static final int WIDTH = Main.HALF_WIDTH; //Width of the Side-Panel
    public static final int HEIGHT = Main.HEIGHT - PREV_HEIGHT; //Height of the VBox

    @FXML AnchorPane root;
    static AnchorPane sideBar;

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
