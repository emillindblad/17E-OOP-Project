package edu.tda367.View.scenes;

import edu.tda367.Controllers.MyListingsController;
import edu.tda367.Controllers.RentingItemController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class RentingPage extends AbstractHyroScene {

    private final MyListingsController controller;

    @FXML private FlowPane listingsPane;
    @FXML private FlowPane bookingsPane;

    public RentingPage(SceneHandler handler) throws IOException {
        super("rentingpage", handler);
        controller = new MyListingsController(handler);
    }

    @Override
    public void update() {
        controller.update(listingsPane, bookingsPane);
    }

    @FXML
    private void goBack() {
        controller.goBack();
    }
}
