package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kiosk.backend.Menu;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The NavigationController Class is the controller for the navigation drawer. It is
 * associated with the navigationDrawer.fxml file.
 */
public class NavigationController implements Initializable {
    /**
     * The height of the banner in the navigation drawer.
     */
    public static final int NAV_BANNER_HEIGHT = 170;
    /*
    The width of the navigation drawer.
     */
    public static final int WIDTH = Main.HALF_WIDTH;
    /**
     * The height of the VBox in the navigation drawer. This is equal to the height of the app minus
     * the height of the banner. This VBox contains the buttons in the navigation drawer.
     */
    public static final int HEIGHT = Main.HEIGHT - NAV_BANNER_HEIGHT; //Height of the VBox

    /**
     * The Anchor Pane which contains all of the other nodes.
     */
    @FXML
    AnchorPane sideBar;

    /**
     * Initializes the navigation drawer. Generates the buttons when the navigation drawer is opened.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox buttonContainer = (VBox)sideBar.lookup("#buttonContainer");
        // Create a button for each of the 6 categories
        for (String category : Menu.generateTypes()) {
            Button button = new Button(category);
            button.getStyleClass().add("bt-main");
            // When one of the category buttons is clicked, go to that category's screen
            button.setOnAction((event -> Main.selectCategory(sideBar.getParent(), category)));

            buttonContainer.getChildren().add(button);
        }
    }

    /**
     * This method closes the navigation drawer. It is called when the user pushes the "X" button (hamburger-close).
     */
    @FXML
    public void closeSideMenu() {
        AnchorPane root = (AnchorPane)sideBar.getParent();
        root.getChildren().remove(sideBar);
    }
}