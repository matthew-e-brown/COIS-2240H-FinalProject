package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


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
