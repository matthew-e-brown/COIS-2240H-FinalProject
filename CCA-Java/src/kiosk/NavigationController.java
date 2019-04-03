package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kiosk.backend.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {
    public static final int NAV_BANNER_HEIGHT = 170; //Preview Height
    public static final int WIDTH = Main.HALF_WIDTH; //Width of the Side-Panel
    public static final int HEIGHT = Main.HEIGHT - NAV_BANNER_HEIGHT; //Height of the VBox

    @FXML
    AnchorPane sideBar;

    @Override //Generate the buttons on load
    public void initialize(URL location, ResourceBundle resources) {
        VBox buttonContainer = (VBox)sideBar.lookup("#buttonContainer");
        for (String category : Menu.generateTypes()) {
            Button button = new Button(category);
            button.getStyleClass().add("bt-main");
            button.setOnAction((event -> Main.selectCategory(sideBar.getParent(), category)));

            buttonContainer.getChildren().add(button);
        }
    }

    @FXML
    public void closeSideMenu() {
        AnchorPane root = (AnchorPane)sideBar.getParent();
        root.getChildren().remove(sideBar);
    }
}