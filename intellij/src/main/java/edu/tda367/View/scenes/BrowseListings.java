package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class BrowseListings extends AbstractHyroScene {
    BrowseController browseController;

    @FXML
    FlowPane listProducts;

    /**
     * Constructs the BrowseListings Scene
     * @param handler sends the sceneHandler to super
     * lastly it runs updateList to make sure all content is up to date when scene is created.
     */
    public BrowseListings(SceneHandler handler) throws IOException {
        super("browse", handler);
        browseController = new BrowseController(handler);
        updateList();
    }

    @FXML
    private void switchToCreate() {
        handler.switchTo("createlisting");
        handler.centerOnScreen();
    }


    /**
     * Fetches a list of available listings from the controller and adds them all to the FXML flowpane so that it gets displayed in the GUI
     */
    void updateList() {
        var listings = browseController.getAvailableListings();
        listProducts.getChildren().clear();
        for(int i = 0; i < listings.size(); i++)
        {
            listProducts.getChildren().add(new ListingItem(this.handler, this, listings.get(i).getPrice(), listings.get(i).getProduct().getProdName(), listings.get(i).getProduct().getCategoryName()));
        }
    }

    /**
     * Method runs everytime scene is switched to so flowpane gets updated on scene switch.
     */
    @Override
    public void update() {
        updateList();
    }
}
