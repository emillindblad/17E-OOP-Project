package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
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

    /**
     * If listing is available, updates listing with form data
     * @param formData Data used for listing
     * @param userID UserID related to listing
     * @return Status message
     */
    @Override
    protected String updateListings(String[] formData, int userID) {
        if (listing.getIsAvailable()) {
            listing.setDesc(formData[1]);
            listing.setPrice(Integer.parseInt(formData[2]));
            listing.setFileName(formData[4]);
            return "Success";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ändra annons");
        alert.setHeaderText("Ändra annons");
        String s = "Kan inte ändra annons just nu";
        alert.setContentText(s);

        alert.showAndWait();

        return "Kan inte ändra när varan är bokad!";
    }

    /**
     * Returns to rentingPage
     */
    @Override
    public void goBack() {
        handler.switchTo("rentingpage");
    }

    /**
     * Getter for FXML name to be controlled
     * @return FXML name
     */
    @Override
    public String getFXMLName() {
        return "listingsettings";
    }

    /**
     * Called when scene is switched to. Enters correct info in fields
     * @param nameField Field for product name
     * @param descField Field for product description
     * @param priceField Field for listing price
     * @param categoriesDropdown Dropdown menu for categories
     */
    @Override
    public void update(TextField nameField, TextField descField, TextField priceField, ComboBox<String> categoriesDropdown) {
        nameField.setText(listing.getProductName());
        descField.setText(listing.getProduct().getDescription());
        priceField.setText(String.valueOf(listing.getPrice()));
        categoriesDropdown.setValue(listing.getCategoryName());
        categoriesDropdown.setDisable(true);
        nameField.setDisable(true);
    }

    /**
     * Asks if user is sure they want to delete listing.
     * If yes, deletes listing.
     */
    @Override
    public void secondAction() {
        if (listing.getIsAvailable()) {
            showDeleteListingConfirmation();
        } else {
            showCantDeleteListinInfo();
        }
    }

    private void showDeleteListingConfirmation() {
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

    private void showCantDeleteListinInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ta bort annons?");
        alert.setHeaderText("Ta bort annons?");
        String s = "Kan inte ta bort annons just nu";
        alert.setContentText(s);

        alert.showAndWait();
    }
}
