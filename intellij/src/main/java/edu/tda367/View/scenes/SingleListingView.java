package edu.tda367.View.scenes;

import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SingleListingView extends AbstractHyroScene {



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

    private final String listingId;

    /**
     * Super constructor for all scenes. Loads the FXML file and creates a scene with the loaded fxml file
     * @param fxmlName name of the fxml file that you want to load with the scene
     * @param handler  sceneHandler that is in charge of switching between scenes
     * @param price
     * @param listingId
     */
    public SingleListingView(SceneHandler handler, int user, String productName, int price, String category, String description, String listingId) throws IOException {
        super("SingleListingView", handler);
        this.listingId = listingId;
        userId.setText(String.valueOf(user));
        prodName.setText(productName);
        this.price.setText(String.valueOf(price));
        this.category.setText(category);
        this.description.setText(description);
    }

    @FXML
    public void rentListing()
    {
        System.out.println("renting...");
        //TODO Controller
    }

    @Override
    public void update() {

    }
}
