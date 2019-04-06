package kiosk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kiosk.backend.Menu;
import kiosk.loadIns.CategoryItemController;

import java.net.URL;
import java.util.ResourceBundle;

import static kiosk.Main.order;

/**
 * The CategoryController Class is the controller for the category screens. It is associated
 * with the <code>category.fxml</code> file. It loads several instances of <code>CategoryItemController</code>
 * to fill the screen with each category.
 */
public class CategoryController implements Initializable {
    /**
     * The AnchorPane which contains all of the other nodes.
     */
    @FXML AnchorPane root;
    /**
     * The button which allows the user to open the navigation drawer.
     */
    @FXML Button hamburger;
    /**
     * The button which allows the user to view their order, by switching to the order screen.
     */
    @FXML Button orderButton;
    /**
     * The grid which contains the 6 categories.
     */
    @FXML GridPane typesGrid;
    /**
     * The Pane which displays the number of items the user has added to their order.
     */
    @FXML StackPane numberPane;
    /**
     * Contains the number of food or drink items currently in the user's order.
     */
    @FXML Text orderNumber;
    /**
     * The image at the top of the screen.
     */
    @FXML ImageView banner;

    /**
     * Each instance of CategoryController corresponds to one of 6 categories, given by the <code>category</code> field.
     */
    private String category;

    /**
     * The Anchor Pane which contains the sidebar / navigation drawer.
     */
    static AnchorPane sideBar;

    /**
     * This constructor is used to create an instance of
     * CategoryController for each category.
     * @param category The food category (e.g., Salad, Burgers, etc)
     */
    CategoryController(String category) { this.category = category; }

    /**
     * Initializes the Category screen for the given category.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the banner image for the specific category
        banner.setImage(new Image("file:src/img/Banners/" + this.category + ".png"));

        // Set spacing between nodes in the types grid
        typesGrid.setVgap(15);
        typesGrid.setHgap(15);

        try {
            if (this.category == null) throw new Exception("Type must be set before initialize is run");
            int i = 0, j = 0;
            // Load each item in the given category
            for (String item : Menu.getItemsByType(this.category)) {
                // Load the category item
                CategoryItemController controller = new CategoryItemController(item, this);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("loadIns/categoryItem.fxml"));
                loader.setController(controller);
                StackPane itemPane = loader.load();

                // Display the name of the item on the button
                controller.button.setText(controller.itemName);
                // When the user clicks the button, add the item to the user's order
                controller.button.setOnAction((event) -> controller.addToOrder());

                // Set the image for the item
                String path = Menu.getFilepath(item);
                controller.setImageURL(path);

                typesGrid.add(itemPane, i, j);
                if (++i > 1) {
                    i = 0;
                    j++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        hamburger.setOnAction((event) -> openSideMenu());
        //Make a circle which will display the number of items currently in the user's order
        double width = numberPane.getPrefWidth();
        numberPane.setPrefHeight(width);
        numberPane.setClip(new Circle(width / 2, width / 2, width / 2));

        // Display the number of items in the order if the order length is greater than 1
        int orderLength = order.getLength();
        if (orderLength > 0) {
            numberPane.setVisible(true);
            orderNumber.setText(String.valueOf(orderLength));
        }

        // When the user clicks the "View Order" button, go to the order screen
        orderButton.setOnAction(event -> viewOrder());
    }

    /**
     * Opens the navigation drawer. This method is called when the user presses
     * the hamburger button.
     */
    private void openSideMenu() {
        root.getChildren().add(sideBar);
    }

    /**
     * Increases the number in <code>numberPane</code> on top of the "View Order" button.
     * This method is invoked any time a user adds to their order.
     */
    public void incrementOrderIcon() {
        if (!numberPane.isVisible()) numberPane.setVisible(true); //Show it if it doesn't exist
        int n = Integer.parseInt(this.orderNumber.getText());
        orderNumber.setText(String.valueOf(++n));
    }

    /**
     * Opens the order screen. This method is called when the user
     * presses the "View Order" button.
     */
    private void viewOrder() {
        Stage primaryStage = (Stage)root.getScene().getWindow();
        OrderController controller = Main.getOrderController();
        // Close the sidebar that was left open when you left
        try { controller.root.getChildren().remove(OrderController.sideBar); }
        catch (NullPointerException e) { System.out.println("null"); }
        primaryStage.setScene(Main.getOrderScreen());
    }
}
