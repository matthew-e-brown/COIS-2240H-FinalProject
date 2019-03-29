package kiosk;

/* FXML Imports */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* Other Imports */
import java.util.ArrayList;

public class Main extends Application {
    /* Fields */
    private static Scene splashScreen, homeScreen;
    public static final int WIDTH = 600, HEIGHT = 800;
    public static final int HALF_WIDTH = 300, THIRD_WIDTH = 200;
    public static final int HALF_HEIGHT = 400, QUART_HEIGHT = 200;

    /* Getters */
    static Scene getHomeScreen() { return homeScreen; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent splashRoot = FXMLLoader.load(getClass().getResource("splash.fxml"));
        Parent homeRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        splashScreen = new Scene(splashRoot, WIDTH, HEIGHT);
        homeScreen = new Scene(homeRoot, WIDTH, HEIGHT);

        /* Add SidePanel */
        AnchorPane sideBar = FXMLLoader.load(getClass().getResource("navigationDrawer.fxml"));
        Database DB = new Database("jdbc:sqlite:src/master.db");
        for (String category : Menu.generateTypes(DB)) {
            Button button = new Button(category);
            button.getStyleClass().add("button");
            ((VBox)sideBar.lookup("#buttonContainer")).getChildren().add(button);
        }
        DB.closeConnection();

        HomeController.sideBar = sideBar;

        primaryStage.setTitle("Ordering Kiosk");
        primaryStage.setScene(splashScreen);
        primaryStage.show();
    }

    public static void main(String[] args) {
        /* ↓↓ Testing the DB Functions ↓↓ */
        Database DB = new Database("jdbc:sqlite:src\\master.db");

        ArrayList<String> types = Menu.generateTypes(DB);
        Order.addToOrder(DB, "greasySticks", 3.69F);

        DB.closeConnection();

        /* Open Window */
        launch(args);
    }
}
