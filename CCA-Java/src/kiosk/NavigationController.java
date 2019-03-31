package kiosk;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class NavigationController {
    public static final int NAV_BANNER_HEIGHT = 170; //Preview Height
    public static final int WIDTH = Main.HALF_WIDTH; //Width of the Side-Panel
    public static final int HEIGHT = Main.HEIGHT - NAV_BANNER_HEIGHT; //Height of the VBox

    @FXML
    AnchorPane sideBar;

    @FXML
    public void closeSideMenu() {
        AnchorPane root = (AnchorPane)sideBar.getParent();
        root.getChildren().remove(sideBar);
    }
}