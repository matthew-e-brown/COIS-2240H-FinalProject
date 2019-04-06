package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kiosk.backend.Database;

public class Main extends Application {
    /* Fields */
    /**
     * The instance of Database which allows the app to connect to master.db.
     */
    public static Database DB = new Database("jdbc:sqlite:src/master.db");
    /**
     * The instance of Order which keeps track of the items in the user's order.
     */
    public static Order order = new Order();

    /* Constants */
    private static Scene homeScreen, splashScreen, orderScreen;
    private static OrderController orderController = new OrderController();
    public static final int WIDTH = 600, HEIGHT = 800;
    public static final int HALF_WIDTH = 300;
    static final int QUARTER_HEIGHT = 200;

    /* Getters */
    static Scene getHomeScreen() { return homeScreen; }
    static Scene getSplashScreen() { return splashScreen; }
    static Scene getOrderScreen() { return orderScreen; }
    public static OrderController getOrderController() { return orderController; }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /* Add SidePanel to everything */
        AnchorPane sideBar = FXMLLoader.load(getClass().getResource("navigationDrawer.fxml"));
        HomeController.sideBar = sideBar;
        CategoryController.sideBar = sideBar;
        OrderController.sideBar = sideBar;

        /* Load Every Scene */
        Parent splashRoot = FXMLLoader.load(getClass().getResource("splash.fxml"));
        Parent homeRoot = FXMLLoader.load(getClass().getResource("home.fxml"));

        /* Order is special because I need to use the controller's method outside of initialize */
        FXMLLoader orderLoader = new FXMLLoader(Main.class.getResource("order.fxml"));
        orderLoader.setController(orderController);
        Parent orderRoot = orderLoader.load();

        splashScreen = new Scene(splashRoot, WIDTH, HEIGHT);
        homeScreen = new Scene(homeRoot, WIDTH, HEIGHT);
        orderScreen = new Scene(orderRoot, WIDTH, HEIGHT);

        /* Add CSS to every screen */
        for (Scene scene : new Scene[] { splashScreen, homeScreen, orderScreen }) {
            scene.getStylesheets().addAll(
                    "/css/master.css",
                    "/css/navigation.css"
            );
        }

        primaryStage.setTitle("Ordering Kiosk");
        primaryStage.setScene(splashScreen);
        primaryStage.setOnCloseRequest(event -> DB.closeConnection());
        primaryStage.show();
    }

    /* root: the currently selected window */
    public static void selectCategory(Node root, String category) {
        try {
            //get the primaryStage
            Stage primaryStage = (Stage)root.getScene().getWindow();
            //Get the scene
            CategoryController newController = new CategoryController(category);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("category.fxml"));
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

    public static void main(String[] args) { launch(args); }

    static void resetKiosk(Stage primaryStage) {
        primaryStage.setScene(splashScreen);
    }
}
