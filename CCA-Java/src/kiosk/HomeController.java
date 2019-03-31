package kiosk;

import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public static final int BANNER_HEIGHT = Main.QUARTER_HEIGHT;
    public static final int OBJECT_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.85);
    public static final int OBJECT_SIDE_OFFSET = (int)(Main.WIDTH * 0.065);
    public static final int OBJECT_TOP_POS = BANNER_HEIGHT + OBJECT_SIDE_OFFSET;

    @FXML AnchorPane root;
    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Load the 6 types in here
    }

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
