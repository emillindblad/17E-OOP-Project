package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingState;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Optional;

public class ListingSettingsController extends ListingViewController {

    private final Listing listing;

    public ListingSettingsController(SceneHandler sceneHandler, Listing listing) {
        super(sceneHandler);
        this.listing = listing;
    }

    @Override
    protected String updateListings(String[] formData, int userID) {
        if (listing.getListingState() == ListingState.AVAILABLE) {
            listing.setDesc(formData[1]);
            listing.setPrice(Integer.parseInt(formData[2]));
            listing.setFileName(formData[4]);
            return "Success";
        }
        return "Kan inte ändra när varan är bokad!";
    }

    @Override
    public void goBack() {
        sceneHandler.switchTo("rentingpage");
    }

    @Override
    public String getFXMLName() {
        return "listingsettings";
    }

    @Override
    public void update(TextField nameField, TextField descField, TextField priceField, ComboBox<String> categoriesDropdown) {
        nameField.setText(listing.getProductName());
        descField.setText(listing.getProduct().getDescription());
        priceField.setText(String.valueOf(listing.getPrice()));
        categoriesDropdown.setValue(listing.getCategoryName());
        categoriesDropdown.setDisable(true);
        nameField.setDisable(true);
    }

    @Override
    public void secondAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ta bort annons?");
        alert.setHeaderText("Ta bort annons?");
        String s = "Är du säker?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            listingHandler.removeListing(listing);
            goBack();
        }
    }
}
