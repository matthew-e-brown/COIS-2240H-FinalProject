package kiosk;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.util.Random;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public static final int BANNER_HEIGHT = Main.QUARTER_HEIGHT;
    public static final int OBJECT_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.85);
    public static final int OBJECT_SIDE_OFFSET = (int)(Main.WIDTH * 0.065);
    public static final int OBJECT_TOP_POS = BANNER_HEIGHT + OBJECT_SIDE_OFFSET;
    public static final int GRID_GAP = 15;

    @FXML AnchorPane root;
    @FXML GridPane typesGrid;
    static AnchorPane sideBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> types = Menu.generateTypes();
        Random r = new Random();
        int i = 0;
        int j = 0;
        for (String type : types) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("homeItem.fxml"));
                StackPane item = loader.load();
                // To get access to setter Methods
                HomeItemController controller = loader.getController();
                controller.button.setText(type);

                //Put a random image URL in there
                ArrayList<String> items = Menu.getItemsByType(type);
                String path = Menu.getFilepath(items.get(r.nextInt(items.size())));
                controller.setImageURL(path);

                typesGrid.add(item, i, j);
                i = ++i % 2;
                j = ++j % 3;
            }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
