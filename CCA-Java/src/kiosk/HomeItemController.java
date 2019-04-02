package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomeItemController {
    public static final int IMAGE_WIDTH = 250;
    public static final int IMAGE_HEIGHT = 160;

    private String category;

    @FXML StackPane root;
    @FXML ImageView image;
    @FXML Button button;

    HomeItemController(String category) { this.category = category; }

    void setImageURL(String URL) {
        Image img = new Image("file:src" + URL);
        this.image.setImage(img);
    }

    // Cannot be @FXML because it is referenced sans-Controller
    void changeScene() {
        try {
            //get the primaryStage
            Stage primaryStage = (Stage) root.getScene().getWindow();
            //Get the scene
            CategoryController newController = new CategoryController(this.category);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("category.fxml"));
            loader.setController(newController);
            //Change the scene
            Scene categoryScene = new Scene(loader.load(), Main.WIDTH, Main.HEIGHT);
            categoryScene.getStylesheets().addAll(
                    "/css/master.css",
                    "/css/navigation.css"
            );

            primaryStage.setScene(categoryScene);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
