package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.RentingItemController;
import edu.tda367.Model.RentingItemEntry;
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
    private final RentingItemEntry entry;

    public RentingItem(RentingItemController controller, RentingItemEntry entry) {
        FXMLLoader loader = App.loadFXML("myrentingitem");
        loader.setRoot(this);
        loader.setController(this);
        this.controller = controller;
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.entry = entry;
        initialize(entry);
    }


    @FXML
    private void buttonAction() {
        controller.buttonAction(entry);
        initialize(entry);
    }

    private void initialize(RentingItemEntry entry) {
        productLabel.setText(entry.getProductName());
        priceLabel.setText(String.valueOf(entry.getPrice()));
        categoryLabel.setText(entry.getCategoryName());
        statusLabel.setText(entry.getStatusText());
        button.setText(entry.getButtonText());
        //image.setImage(entry.getImage());
    }


}
