package edu.tda367.View.scenes;

import edu.tda367.Controllers.MyListingsController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;

import java.io.IOException;

public class RentingPage extends AbstractHyroScene {

    private final MyListingsController controller;

    public RentingPage(SceneHandler handler) throws IOException {
        super("rentingpage", handler);
        controller = new MyListingsController(handler);
    }

    @Override
    public void update() {

    }

    @FXML
    private void goBack() {
        controller.goBack();
    }
}
