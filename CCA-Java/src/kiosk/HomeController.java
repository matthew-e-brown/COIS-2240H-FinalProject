package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kiosk.backend.Menu;
import kiosk.loadIns.HomeItemController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
        Random r = new Random();
        int i = 0, j = 0;
        try {
            for (String category : Menu.generateTypes()) {
                HomeItemController controller = new HomeItemController(category);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("loadIns/homeItem.fxml"));
                loader.setController(controller);
                StackPane itemPane = loader.load();
                // To get access to setter Methods
                controller.button.setText(category);
                controller.button.setOnAction((event) -> controller.changeScene());

                //Put a random image URL in there
                ArrayList<String> items = Menu.getItemsByType(category);
                String path = Menu.getFilepath(items.get(r.nextInt(items.size())));
                controller.setImageURL(path);

                typesGrid.add(itemPane, i, j); //Will automatically make new rows
                if (++i > 1) {
                    i = 0;
                    j++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
