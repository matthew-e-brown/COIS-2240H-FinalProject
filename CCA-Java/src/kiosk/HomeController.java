package kiosk;

import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public static final int BANNER_HEIGHT = Main.QUARTER_HEIGHT;
    public static final int OBJECT_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.85);
    public static final int OBJECT_SIDE_OFFSET = (int)(Main.WIDTH * 0.065);
    public static final int OBJECT_TOP_POS = BANNER_HEIGHT + OBJECT_SIDE_OFFSET;

    @FXML AnchorPane root;
    @FXML GridPane typesGrid;
    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> types = Menu.generateTypes();
        int i = 0;
        int j = 0;
        for (String t : types) {
            Pane p = new Pane(new Button(t));
            typesGrid.add(p, i, j);
            i = ++i % 2;
            j = ++j % 3;
        }
    }

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
