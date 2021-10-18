package edu.tda367.View.scenes;

import edu.tda367.Controllers.MyListingsController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

/**
 * FXML Controller class for RentingPage
 */
public class RentingPage extends AbstractHyroScene {

    private final MyListingsController controller;

    @FXML private FlowPane listingsPane;
    @FXML private FlowPane bookingsPane;

    /**
     * Constructor. Sets up JavaFX
     * @param handler SceneHandler for controller to use for changing scenes
     * @throws IOException
     */
    public RentingPage(SceneHandler handler) throws IOException {
        super("rentingpage", handler);
        controller = new MyListingsController(handler);
    }

    /**
     * Called when scene is switched to
     * Updates lists of Listings and Bookings
     * Chains call to controller
     */
    @Override
    public void update() {
        listingsPane.getChildren().clear();
        bookingsPane.getChildren().clear();
        controller.update(listingsPane, bookingsPane);
    }

    /**
     * Goes back to browse page
     * Chains call to controller
     */
    @FXML
    private void goBack() {
        controller.goBack();
    }
}
