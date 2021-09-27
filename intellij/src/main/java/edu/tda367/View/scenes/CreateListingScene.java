package edu.tda367.View.scenes;

import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class CreateListingScene extends AbstractHyroScene {
    //private final ListingController listingController;
    private final ListingHandler listingHandler;

    @FXML private TextField productName;
    @FXML private TextField productDesc;
    @FXML private TextField prodPrice;
    @FXML private DatePicker prodAvail;
    @FXML private ComboBox<String> categoriesDropdown;

    public CreateListingScene(SceneHandler handler) throws IOException {
        super("createlisting",handler);
        this.listingHandler = new ListingHandler();

        this.categoriesDropdown.getItems().setAll(loadCategories());
        System.out.println("Create Listing now");
    }

    public ArrayList<String> loadCategories() {
        return listingHandler.getCategoryNames();
    }

    public String[] getFormInput() {
        String[] formData = {productName.getText(),productDesc.getText(),prodPrice.getText(),categoriesDropdown.getSelectionModel().getSelectedItem()};
        return formData;
    }

    @FXML
    public void createListing() {
        String[] formData = getFormInput();
        listingHandler.createListingFromString(formData);
        System.out.println("Created listing");
        switchToBrowse();
    }

    private void switchToBrowse() {
        handler.switchTo("browse");
        handler.centerOnScreen();
    }
}
