package kiosk;

/* FXML Imports */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* Other Imports */
import java.util.ArrayList;

public class Main extends Application {
    private static Scene splashScreen, homeScreen;
    public static final double WIDTH = 600, HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        splashScreen = new Scene(FXMLLoader.load(getClass().getResource("splash.fxml")), WIDTH, HEIGHT);
        homeScreen = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")), WIDTH, HEIGHT);

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
