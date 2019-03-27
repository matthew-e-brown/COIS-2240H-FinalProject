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
        ArrayList<String> types = generateTypes();
        for (String t : types) {
            System.out.println(t);
        }
       // closeConnection(statement, conn);
    }

    public static Connection connectToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src\\menu.db");
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return statement;
    }

    public static void closeConnection(Statement statement, Connection conn) {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
    public static ArrayList<String> generateTypes(){
        ArrayList<String> types = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src\\menu.db");
            System.out.println(conn);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT type FROM menu");
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return types;
    }
}
