package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class HomeController {
    /* Dimensions for VBox */
    public static final int PREV_HEIGHT = 80; //Preview Height
    public static final int WIDTH = Main.HALF_WIDTH; //Width of the Side-Panel
    public static final int HEIGHT = Main.HEIGHT - PREV_HEIGHT; //Height of the VBox

    @FXML AnchorPane root;
    private boolean navOpen = false;
    public static AnchorPane sideBar;

    @FXML
    public void toggleSideMenu() {
        if (!navOpen) root.getChildren().add(sideBar);
        else root.getChildren().remove(sideBar);
        navOpen = !navOpen;
    }
}
