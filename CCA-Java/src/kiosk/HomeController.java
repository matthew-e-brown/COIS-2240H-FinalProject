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
    /**
     * The height of the banner on each screen. This is equal
     * to 1/4 of the height of the app.
     */
    public static final int BANNER_HEIGHT = Main.QUARTER_HEIGHT;

    /**
     * The height of the scroll panes on the home screen and category screens.
     */
    public static final int OBJECT_HEIGHT = (int)((Main.HEIGHT - BANNER_HEIGHT) * 0.85);

    /**
     * The side offset for objects on the home screen, category screens, and order screen.
     */
    public static final int OBJECT_SIDE_OFFSET = (int)(Main.WIDTH * 0.05);

    /**
     * The Y-position of objects below banners.
     */
    public static final int OBJECT_TOP_POS = BANNER_HEIGHT + OBJECT_SIDE_OFFSET;

    /**
     * The spacing between items in the grid containing the 6 different categories.
     */
    public static final int GRID_GAP = 15;

    /**
     * The Anchor Pane which contains all of the other nodes, on the home screen.
     */
    @FXML AnchorPane root;

    /**
     * The Grid Pane which contains the 6 different categories on the home screen.
     */
    @FXML GridPane typesGrid;

    /**
     * The Anchor Pane which contains the sidebar / navigation drawer.
     */
    static AnchorPane sideBar;

    /**
     * Initializes the home screen.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create instance of Random class in order to randomly choose an image for each category
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
                controller.button.setOnAction((event) -> controller.selectCategory());

                // Randomly pick one of the items in the category and set the category image to an image of this item
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

    /**
     * Opens the navigation drawer. This method is called when the user presses
     * the hamburger button.
     */
    @FXML
    public void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
