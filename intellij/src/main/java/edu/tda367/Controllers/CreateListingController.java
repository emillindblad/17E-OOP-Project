package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateListingController extends ListingViewController {

    public CreateListingController(SceneHandler sceneHandler) {
        super(sceneHandler);
    }

    /**
     * Creates a listing using input data
     * @param formData Data used for listing
     * @param userID UserID related to listing
     * @return Status message
     */
    @Override
    protected String updateListings(String[] formData, int userID) {
        listingHandler.createListing(formData, userID);
        return "Success";
    }


    /**
     * Returns to browse view
     */
    @Override
    public void goBack() {
        sceneHandler.switchTo("browse");
    }

    /**
     * Getter for FXML name to be controlled
     * @return FXML name
     */
    @Override
    public String getFXMLName() {
        return "createlisting";
    }

    /**
     * Called when controlled scene is switched to. Clears all fields
     * @param nameField Field for product name
     * @param descField Field for product description
     * @param priceField Field for listing price
     * @param categoriesDropdown Dropdown menu for categories
     */
    @Override
    public void update(TextField nameField, TextField descField, TextField priceField, ComboBox<String> categoriesDropdown) {
        nameField.clear();
        descField.clear();
        priceField.clear();
        categoriesDropdown.setValue(null);
    }

    /**
     * Cancels listing creation
     */
    @Override
    public void secondAction() {
        goBack();
    }

}
