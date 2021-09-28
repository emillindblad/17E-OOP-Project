package edu.tda367.View.scenes;

import edu.tda367.InputChecker;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateListingScene extends AbstractHyroScene {
    //private final ListingController listingController;
    private final ListingHandler listingHandler;

    @FXML private TextField productName;
    @FXML private TextField productDesc;
    @FXML private TextField prodPrice;
    @FXML private Label errorMsg;
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
        return new String[]{productName.getText(),productDesc.getText(),prodPrice.getText(),categoriesDropdown.getSelectionModel().getSelectedItem()};
    }

    @FXML
    public boolean validateData(String[] formData) {
        if (!InputChecker.checkForNumber(formData[2])) {
            errorMsg.setText("WRONG");
            return false;
        }
        return true;
    }

    @FXML
    public void createListing() {
        String[] formData = getFormInput();
        System.out.println(Arrays.asList(formData));
        if (validateData(formData)) {
            listingHandler.createListingFromString(formData);
            System.out.println("Created listing");
            switchToBrowse();
        }
    }

    private void switchToBrowse() {
        handler.switchTo("browse");
        handler.centerOnScreen();
    }
}
