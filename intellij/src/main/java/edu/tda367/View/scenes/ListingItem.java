package edu.tda367.View.scenes;


import edu.tda367.App;
import edu.tda367.Controllers.BrowseController;
import edu.tda367.Controllers.ImageHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    private final String listingId;

    @FXML
    private ImageView listingImage;

    /**
     * Constructor for the ListingItem which is a component that gets loaded in the BrowseListings Scenes flowpoane
     * @param price price for the product that is listed
     * @param productName name of the product that is listed
     * @param productCategory category name for the product that is listed
     * @param listingId
     */

    protected ListingItem(SceneHandler handler, hyroScene scene, String[] listingData) {
        this.listingId = listingData[0];
        System.out.println("-------ListingItem-----");
        System.out.println(listingId);
        FXMLLoader loader = App.loadFXML("listingitem");
        loader.setRoot(this);
        loader.setController(this);
        this.scene = scene;
        controller = new BrowseController(handler);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize(listingData);
    }

    protected ListingItem(SceneHandler handler, hyroScene scene, int price, String productName, String productCategory, String listingId, String imagePath) {
        this.listingId = listingId;
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
        this.listingImage.setImage(ImageHandler.getInstance().getImage(imagePath));

    }

    public void switchToListing() throws IOException {
        controller.switchToListing(this.listingId);
    }

    void initialize(String[] listingData) {
        this.itemProductName.setText(listingData[1]);
        this.itemCategory.setText(listingData[2]);
        //Descripion this.itemDesc.setText(listingData[3]);
        this.itemPrice.setText(listingData[4] + " Kr");
        this.listingImage.setImage(ImageHandler.getInstance().getImage(listingData[5]));

        this.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
    }
}
