package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.View.SceneHandler;
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

    void updateList()
    {
        var listings = browseController.getAvailableListings();
        for(int i = 0; i < listings.size(); i++)
        {
            listProducts.getChildren().add(new Label(listings.get(i).getProduct().toString()));
        }
    }

}
