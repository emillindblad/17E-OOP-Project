package edu.tda367.Controllers;

import edu.tda367.Model.RentingItemEntry;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.TextField;

import java.io.File;

public class ListingSettingsController extends ListingViewController {

    private final RentingItemEntry entry;

    public ListingSettingsController(SceneHandler sceneHandler, RentingItemEntry entry) {
        super(sceneHandler);
        this.entry = entry;
    }

    @Override
    protected void updateListings(String[] formData, int userID) {

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
    public void update(TextField nameField, TextField descField, TextField priceField) {
        nameField.setText(entry.getProductName());
    }
}
