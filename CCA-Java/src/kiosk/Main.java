package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application {
    /* Fields */
    static Database DB = new Database("jdbc:sqlite:src/master.db");

    /* Constants */
    private static Scene splashScreen, homeScreen;
    public static final int WIDTH = 600, HEIGHT = 800;
    public static final int HALF_WIDTH = 300, THIRD_WIDTH = 200;
    public static final int HALF_HEIGHT = 400, QUARTER_HEIGHT = 200;

    /* Getters */
    static Scene getHomeScreen() { return homeScreen; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Add to Controller, then initialize it */
        /* Add SidePanel to everything */
        AnchorPane sideBar = FXMLLoader.load(getClass().getResource("navigationDrawer.fxml"));
        VBox buttonContainer = (VBox)sideBar.lookup("#buttonContainer");
        for (String category : Menu.generateTypes()) {
            Button button = new Button(category);
            button.getStyleClass().add("bt-main");
            buttonContainer.getChildren().add(button);
        }
        HomeController.sideBar = sideBar;
        OrderController.sideBar = sideBar;

        Parent splashRoot = FXMLLoader.load(getClass().getResource("splash.fxml"));
        Parent homeRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        splashScreen = new Scene(splashRoot, WIDTH, HEIGHT);
        homeScreen = new Scene(homeRoot, WIDTH, HEIGHT);

        /* Add CSS to home-screen */
        homeScreen.getStylesheets().addAll(
                "/css/master.css",
                "/css/navigation.css"
        );

        primaryStage.setTitle("Ordering Kiosk");
        primaryStage.setScene(splashScreen);
        primaryStage.setOnCloseRequest(event -> DB.closeConnection());
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
