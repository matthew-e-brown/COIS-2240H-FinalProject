package kiosk;

/* FXML Imports */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/* Other Imports */
import java.util.ArrayList;

public class Main extends Application {
    /* Fields */
    private static Scene splashScreen, homeScreen;
    public static final int WIDTH = 600, HEIGHT = 800;
    public static final int HALF_WIDTH = 300, THIRD_WIDTH = 200;
    public static final int HALF_HEIGHT = 400, QUARTER_HEIGHT = 200;

    /* Getters */
    static Scene getHomeScreen() { return homeScreen; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Add SidePanel */
        AnchorPane sideBar = FXMLLoader.load(getClass().getResource("navigationDrawer.fxml"));
        Database DB = new Database("jdbc:sqlite:src/master.db");
        VBox buttonContainer = (VBox)sideBar.lookup("#buttonContainer");
        for (String category : Menu.generateTypes(DB)) {
            Button button = new Button(category);
            button.getStyleClass().add("bt-main");
            buttonContainer.getChildren().add(button);
        }

        TableView<Item> table;
        Order order = new Order();
        order.addToOrder("greasySticks", 3.69F);
        order.addToOrder("vanilla cone", 2.0F);
        order.addToOrder("greasySticks", 3.69F);

        //Name column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //Price column
        TableColumn<Item, Float> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //Quantity column
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        table = new TableView<>();
        table.setItems(order.getItems());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        HomeController.dick = vBox;

        DB.closeConnection();

        HomeController.sideBar = sideBar;

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
        primaryStage.show();
    }

    public static void main(String[] args) {
        /* ↓↓ Testing the DB Functions ↓↓ */
        Database DB = new Database("jdbc:sqlite:src\\master.db");
        Order order = new Order();

        ArrayList<String> types = Menu.generateTypes(DB);
        order.addToOrder("greasySticks", 3.69F);
        order.addToOrder("vanilla cone", 2.0F);
        order.addToOrder("greasySticks", 3.69F);
        System.out.println(order.calculateSubtotal());
        order.removeFromOrder("greasySticks");
        order.resetOrder();

        DB.closeConnection();

        /* Open Window */
        launch(args);
    }
}
