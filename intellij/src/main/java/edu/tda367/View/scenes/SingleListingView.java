package edu.tda367.View.scenes;

import edu.tda367.Controllers.RentController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SingleListingView extends AbstractHyroScene {

    RentController rentController;

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

    /**
     * Super constructor for all scenes. Loads the FXML file and creates a scene with the loaded fxml file
     * @param handler  sceneHandler that is in charge of switching between scenes
     * @param price
     * @param listingId
     */
    public SingleListingView(SceneHandler handler, int user, String productName, int price, String category, String description, String listingId) throws IOException {
        super("SingleListingView", handler);
        this.listingId = listingId;
        userId.setText(Integer.toString(user));
        prodName.setText(productName);
        this.price.setText(price + " Kr");
        this.category.setText(category);
        this.description.setText(description);
        rentController = new RentController(handler);
    }

    @FXML
    public void rentListing()
    {
        rentController.rentListing(listingId);
        System.out.println("renting...");
        //TODO Controller
    }

    @FXML
    public void goBack(){
        handler.switchTo("browse");
    }
    @Override
    public void update() {
    }
}
