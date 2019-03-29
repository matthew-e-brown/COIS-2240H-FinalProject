package kiosk;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SplashController {
    @FXML private ImageView imgSplashScreen;

    @FXML /* Swaps to the homeScreen scene */
    void triggerSplash() {
        Stage primaryStage = (Stage)imgSplashScreen.getScene().getWindow();
        primaryStage.setScene(kiosk.Main.getHomeScreen());
    }
}
