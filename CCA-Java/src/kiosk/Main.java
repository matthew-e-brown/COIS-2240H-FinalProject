package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static AnchorPane root;
    static List<GridPane> grid = new ArrayList<>();
    private static int currentIndex = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Root pane to hold everything
        root = FXMLLoader.load(getClass().getResource("root.fxml"));

        // Add all the screens
        grid.add(FXMLLoader.load(getClass().getResource("home.fxml")));

        // Set default scene
        root.getChildren().add(grid.get(0));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void setPane(int index) {
        root.getChildren().remove(grid.get(currentIndex));
        root.getChildren().add(grid.get(index));
        currentIndex = index;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
