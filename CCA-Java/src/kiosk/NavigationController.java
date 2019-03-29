package kiosk;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class NavigationController {
    @FXML
    AnchorPane sideBar;

    @FXML
    public void closeSideMenu() {
        AnchorPane root = (AnchorPane)sideBar.getParent();
        root.getChildren().remove(sideBar);
    }
}