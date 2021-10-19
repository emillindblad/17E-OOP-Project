package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CreateListingController extends ListingViewController {

    public CreateListingController(SceneHandler sceneHandler) {
        super(sceneHandler);
    }

    @Override
    protected void updateListings(String[] formData, int userID) {
        listingHandler.createListingFromForm(formData, userID);
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
    public void update(TextField nameField, TextField descField, TextField priceField) {
        nameField.clear();
        descField.clear();
        priceField.clear();
    }

}
