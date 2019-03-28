package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene splashScreen, homeScreen;
    public static final double WIDTH = 600, HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Root pane to hold everything
        splashScreen = new Scene(FXMLLoader.load(getClass().getResource("splash.fxml")), WIDTH, HEIGHT);
        homeScreen = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")), WIDTH, HEIGHT);

        primaryStage.setTitle("Ordering Kiosk");
        primaryStage.setScene(splashScreen);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
