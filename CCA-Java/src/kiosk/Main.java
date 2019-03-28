package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        // launch(args);
        Database DB = new Database("jdbc:sqlite:src\\menu.db");

        ArrayList<String> types = Menu.generateTypes(DB);
        Order.addToOrder(DB, "greasysticks", 3.69F);
        // need to delete the orders table when the customer is finished their transaction / when program ends
        DB.closeConnection();
        /*for (String t : types) {
            ArrayList<String> items = itemsFromType(statement, t);
            System.out.println("Type: " + t);
            for (String x : items){
                System.out.println(x);
            }
            System.out.println("");
        }
        closeConnection(statement, conn);*/
    }
}
