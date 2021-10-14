package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.RentingItemController;
import edu.tda367.Model.Booking.Booking;
import edu.tda367.Model.Listing.Listing;
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

    public RentingItem(RentingItemController controller, Listing listing) {
        FXMLLoader loader = App.loadFXML("myrentingitem");
        loader.setRoot(this);
        loader.setController(this);
        this.controller = controller;
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initialize(listing);
    }

    public RentingItem(RentingItemController controller, Booking booking) {
        FXMLLoader loader = App.loadFXML("myrentingitem");
        loader.setRoot(this);
        loader.setController(this);
        this.controller = controller;
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initialize(booking);
    }

    @FXML
    private void buttonAction() {
        controller.buttonAction(statusLabel, button);
    }

    private void initialize(Listing listing) {
        productLabel.setText(listing.getProduct().getProdName());
        priceLabel.setText(String.valueOf(listing.getPrice()));
        categoryLabel.setText(listing.getProduct().getCategoryName());
        //image.setImage(listing.getImage());
    }
    private void initialize(Booking booking) {
        productLabel.setText(booking.getListing().getProduct().getProdName());
        priceLabel.setText(String.valueOf(booking.getListing().getPrice()));
        categoryLabel.setText(booking.getListing().getProduct().getCategoryName());
        //image.setImage(booking.getListing().getImage());
    }

}
