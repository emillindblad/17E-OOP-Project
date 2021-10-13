package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.RentingItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RentingItem extends AnchorPane {

    @FXML private Label productLabel;
    @FXML private Label priceLabel;
    @FXML private Label categoryLabel;
    @FXML private Button button;
    @FXML private Label statusLabel;
    @FXML private ImageView image;

    private final RentingItemController controller;

    protected RentingItem(RentingItemController controller) {
        FXMLLoader loader = App.loadFXML("myrentingitem");
        loader.setRoot(this);
        loader.setController(this);
        this.controller = controller;
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void buttonAction() {
        controller.buttonAction(statusLabel, button);
    }
}
