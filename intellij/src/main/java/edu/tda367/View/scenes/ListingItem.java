package edu.tda367.View.scenes;


import edu.tda367.App;
import edu.tda367.Controllers.BrowseController;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class ListingItem extends AnchorPane {
    @FXML
    private Label itemPrice;

    @FXML
    private Label itemProductName;

    @FXML
    private Label itemCategory;

    private BrowseController controller;

    private hyroScene scene;

    /**
     * Constructor for the ListingItem which is a component that gets loaded in the BrowseListings Scenes flowpoane
     * @param price price for the product that is listed
     * @param productName name of the product that is listed
     * @param productCategory category name for the product that is listed
     * Loads a FXML file and does the necessary setup with loading etc. Also intializes the ListingItem with the parameters
     */

    protected ListingItem(SceneHandler handler, hyroScene scene, int price, String productName, String productCategory) {
        FXMLLoader loader = App.loadFXML("listingitem");
        System.out.println(loader.toString());
        loader.setRoot(this);
        loader.setController(this);
        this.scene = scene;
        controller = new BrowseController(handler);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize(price, productName, productCategory);
    }

    public void switchToListing() throws IOException {
        controller.switchToListing(this.itemProductName.getText().strip());
    }

    void initialize(int price, String productName, String itemCategory) {
        this.itemProductName.setText(productName);
        this.itemPrice.setText(String.valueOf(price));
        this.itemCategory.setText(itemCategory);
    }


}
