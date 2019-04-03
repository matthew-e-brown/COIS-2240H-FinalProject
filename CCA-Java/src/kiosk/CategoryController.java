package kiosk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import kiosk.backend.Menu;
import kiosk.loadIns.CategoryItemController;

public class CategoryController implements Initializable {
    @FXML AnchorPane root;
    @FXML Button hamburger;
    @FXML GridPane typesGrid;

    private String category;
    static AnchorPane sideBar;

    CategoryController(String category) { this.category = category; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typesGrid.setVgap(15);
        typesGrid.setHgap(15);

        try {
            if (this.category == null) throw new Exception("Type must be set before initialize is run");
            int i = 0, j = 0;
            for (String item : Menu.getItemsByType(this.category)) {
                // Load the category item
                CategoryItemController controller = new CategoryItemController(item);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("loadIns/categoryItem.fxml"));
                loader.setController(controller);
                StackPane itemPane = loader.load();

                controller.button.setText(controller.itemName);
                controller.button.setOnAction((event) -> controller.addToOrder());

                //Put a image URL
                String path = Menu.getFilepath(item);
                controller.setImageURL(path);

                typesGrid.add(itemPane, i, j);
                if (++i > 1) {
                    i = 0;
                    j++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        hamburger.setOnAction((event) -> openSideMenu());
    }

    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }
}
