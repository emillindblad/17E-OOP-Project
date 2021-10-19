package edu.tda367.View.scenes;


import edu.tda367.App;
import edu.tda367.Controllers.BrowseController;
import edu.tda367.Controllers.ImageHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
     * Contructs a simple FXML component for a listing used as an item in the browsing view
     * @param handler
     * @param listingData
     */
    protected ListingItem(SceneHandler handler, String[] listingData) {
        this.listingId = listingData[0];
        System.out.println("-------ListingItem-----");
        System.out.println(listingId);
        System.out.println(listingData[6] + listingData[5]);
        FXMLLoader loader = App.loadFXML("listingitem");
        loader.setRoot(this);
        loader.setController(this);
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

    /**
     * Gets the image from fileName using the ImageHandler
     * @param fileName
     * @return Image found by the ImageHandler
     */
    public Image getImage(String fileName)
    {
        return ImageHandler.getInstance().getImage(fileName);
    }

    /**
     * Sets all the FXML mandatory FXML components that are on a listing
     *
     * @param listingData listing containing necessary data about a listing index 0 is listingId, index 1 is productName, index 2 is categoryName, index 3 is itemDescription, index 4 is price, index 5 is listingState, index 6 is fileName
     */
    void initialize(String[] listingData) {
        this.itemProductName.setText(listingData[1]);
        this.itemCategory.setText(listingData[2]);
        //Descripion this.itemDesc.setText(listingData[3]);
        this.itemPrice.setText(listingData[4] + " Kr");
        this.listingImage.setImage(getImage(listingData[6]));
        System.out.println("code gets here");
        this.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
    }
}
