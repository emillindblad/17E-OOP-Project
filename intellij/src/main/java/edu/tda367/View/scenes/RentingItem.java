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

/**
 * FXML controller class for individual entry on RentingPage
 */
public class RentingItem extends AnchorPane {

    @FXML private Label productLabel;
    @FXML private Label priceLabel;
    @FXML private Label categoryLabel;
    @FXML private Button button;
    @FXML private Label statusLabel;
    @FXML private ImageView image;

    private final RentingItemController controller;
    private final RentingItemEntry entry;

    /**
     * Contructor. Sets up necessary JavaFX parameters
     * Sets labels and button to the right text
     * @param controller MVC controller
     * @param entry the Listing/Booking this entry displays
     */
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

    /**
     * Chains call to controller class
     * Updates state of labels
     */
    @FXML
    private void buttonAction() {
        controller.buttonAction(entry);
        initialize(entry);
    }

    /**
     * Updates texts on labels and button
     * @param entry the RentingItemEntry containing information for label/button text
     */
    private void initialize(RentingItemEntry entry) {
        productLabel.setText(entry.getProductName());
        priceLabel.setText(String.valueOf(entry.getPrice()));
        categoryLabel.setText(entry.getCategoryName());
        statusLabel.setText(entry.getStatusText());
        button.setText(entry.getButtonText());
        
        button.setVisible(!button.getText().equals(""));
        //image.setImage(entry.getImage());
    }


}
