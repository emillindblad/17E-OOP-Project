package edu.tda367.View.scenes;


import edu.tda367.App;
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
    private Label productCategory;

    /**
     * Constructor for the ListingItem which is a component that gets loaded in the BrowseListings Scenes flowpoane
     * @param price price for the product that is listed
     * @param productName name of the product that is listed
     * @param productCategory category name for the product that is listed
     * Loads a FXML file and does the necessary setup with loading etc. Also intializes the ListingItem with the parameters
     */
    protected ListingItem(int price, String productName, String productCategory) {
        FXMLLoader loader = App.loadFXML("listingitem");
        System.out.println(loader.toString());
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize(price, productName, productCategory);
    }

    void initialize(int price, String productName, String itemCategory) {
        this.itemProductName.setText(productName);
        this.itemPrice.setText(String.valueOf(price));
        this.productCategory.setText(itemCategory);
    }

}
