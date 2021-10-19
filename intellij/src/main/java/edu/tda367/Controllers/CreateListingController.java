package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateListingController extends ListingViewController {

    public CreateListingController(SceneHandler sceneHandler) {
        super(sceneHandler);
    }

    @Override
    protected String updateListings(String[] formData, int userID) {
        listingHandler.createListing(formData, userID);
        return "Success";
    }


    @Override
    public void goBack() {
        sceneHandler.switchTo("browse");
    }

    @Override
    public String getFXMLName() {
        return "createlisting";
    }

    @Override
    public void update(TextField nameField, TextField descField, TextField priceField, ComboBox<String> categoriesDropdown) {
        nameField.clear();
        descField.clear();
        priceField.clear();
        categoriesDropdown.setValue(null);
    }

}
