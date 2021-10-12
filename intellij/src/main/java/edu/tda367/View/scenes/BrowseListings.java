package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.View.SceneHandler;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class BrowseListings extends AbstractHyroScene {
    BrowseController browseController;

    @FXML
    FlowPane listProducts;

    public BrowseListings(SceneHandler handler) throws IOException {
        super("browse", handler);
        browseController = new BrowseController();
        System.out.println("nice");
        updateList();
    }

    @FXML
    private void switchToCreate() {
        handler.switchTo("createlisting");
        handler.centerOnScreen();
    }

    @FXML
    private void switchToUserSettings() {
        handler.switchTo("myaccount");
        handler.centerOnScreen();
    }

    @FXML
    private void goToListings() {
        handler.switchTo("mylistings");
        handler.centerOnScreen();
    }


    void updateList() {
        System.out.println("Loaded products");
        ArrayList<Listing> listings = browseController.getAvailableListings();
        System.out.println("Current listings:"+listings.size());
        listProducts.getChildren().clear();
        for(int i = 0; i < listings.size(); i++)
        {
            //System.out.println((listings.get(i).getPrice()));
            listProducts.getChildren().add(new ListingItem(listings.get(i).getPrice(), listings.get(i).getProduct().getProdName(), listings.get(i).getProduct().getCategoryName()));
            //System.out.println(listProducts.getChildren().get(0));
        }
    }

    @Override
    public void update() {
        updateList();
    }
}
