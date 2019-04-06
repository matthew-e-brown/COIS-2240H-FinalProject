package kiosk.loadIns;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import kiosk.CategoryController;
import kiosk.backend.Menu;

import static kiosk.Main.order;

/**
 * The controller for each food-item the user can choose from. An instance of
 * <code>CategoryController</code> will load as many of these (along with
 * it's associated <code>.fxml</code> file) into the program as there are
 * items in its category. This controller controls the button and image for each
 * food item.
 */
public class CategoryItemController {
    @FXML public StackPane root;
    @FXML public ImageView image;
    @FXML public Button button;

    public String itemName;
    private CategoryController parentController;

    /**
     * Constructor used to tell the <code>.fxml</code> file which items to load into place.
     * @param itemName The official name of the food item in question.
     * @param parent The <code>CategoryController</code> which loaded in this CategoryItem.
     *               this is used to trigger a method inside that category's screen.
     */
    public CategoryItemController(String itemName, CategoryController parent) {
        this.itemName = itemName;
        this.parentController = parent;
    }

    /**
     * Sets the image of the CategoryItem.
     * @param URL The location of the image.
     */
    public void setImageURL(String URL) {
        Image img = new Image("file:src" + URL);
        this.image.setImage(img);
    }

    /**
     * Adds the item that this <code>CategoryItem</code> references to the
     * Main order.
     */
    @FXML
    public void addToOrder() {
        order.addToOrder(itemName, Menu.getPrice(this.itemName));
        parentController.incrementOrderIcon();
    }
}
