package kiosk;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The SplashController Class controls the splash screen, which
 * is what the user sees when first opening the app. This class
 * is associated with the splash.fxml file.
 */
public class SplashController {
    /**
     * The image displayed at the start of the app.
     */
    @FXML private ImageView imgSplashScreen;

    /**
     * Switches the scene to the homeScreen. This method
     * is called when the user taps anywhere on the splash screen.
     */
    @FXML
    void triggerSplash() {
        Stage primaryStage = (Stage)imgSplashScreen.getScene().getWindow();
        primaryStage.setScene(kiosk.Main.getHomeScreen());
    }
}
