package edu.tda367.View.scenes;

import edu.tda367.Controllers.ImageHandler;
import edu.tda367.Controllers.SingleListingController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SingleListingView extends AbstractHyroScene {

    SingleListingController singleListingController;

    @FXML
    private Label userId;
    @FXML
    private Label prodName;
    @FXML
    private Label price;
    @FXML
    private Label category;
    @FXML
    private Label description;
    @FXML
    private ImageView prodImage;

    private final String listingId;

    @FXML
    private ImageView listingImage;

    @FXML
    private Button rentButton;

    @FXML
    private Label confirmation;

    /**
     * Super constructor for all scenes. Loads the FXML file and creates a scene with the loaded fxml file
     * @param handler  sceneHandler that is in charge of switching between scenes
     * @param price
     * @param listingId
     */
    public SingleListingView(SceneHandler handler, int user, String productName, int price, String category, String description, String listingId, String listingImage) throws IOException {
        super("SingleListingView", handler);
        this.listingId = listingId;
        userId.setText(String.valueOf(user));
        prodName.setText(productName);
        this.price.setText(String.valueOf(price));
        this.category.setText(category);
        this.description.setText(description);
        this.listingImage.setImage(ImageHandler.getInstance().getImage(listingImage));
        singleListingController = new SingleListingController(handler);
        rentButton.setVisible(!singleListingController.isMyListing(user));
    }

    @FXML
    public void rentListing()
    {
        singleListingController.rentListing(listingId);
        rentButton.setVisible(false);
        confirmation.setVisible(true);
    }

    @FXML
    public void goBack(){
        handler.switchTo("browse");
    }
    @Override
    public void update() {
    }
}
