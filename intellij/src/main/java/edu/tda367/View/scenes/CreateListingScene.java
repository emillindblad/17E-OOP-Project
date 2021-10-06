package edu.tda367.View.scenes;

import edu.tda367.Controllers.ListingController;
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
    private final ListingController listingController;

    @FXML private TextField productName;
    @FXML private TextField productDesc;
    @FXML private TextField prodPrice;
    @FXML private Label errorMsg;
    //@FXML private DatePicker prodAvail;
    @FXML private ComboBox<String> categoriesDropdown;

    private String[] formData = new String[4];

    public CreateListingScene(SceneHandler handler) throws IOException {
        super("createlisting",handler);
        this.listingController = new ListingController(handler);
        this.categoriesDropdown.getItems().setAll(loadCategories());
    }

    private ArrayList<String> loadCategories() {
        return listingController.getCategoryNames();
    }

    private String[] getFormInput() { //TODO Get Availability
        return new String[]{productName.getText(),productDesc.getText(),prodPrice.getText(),categoriesDropdown.getSelectionModel().getSelectedItem()};
    }

    private void reset() {
        this.productName.clear();
        this.productDesc.clear();
        this.prodPrice.clear();
        this.errorMsg.setText("");
        this.categoriesDropdown.setValue(null);
    }

    @FXML
    public void createListing() {
        this.formData = getFormInput();
        errorMsg.setText(listingController.createListing(formData));
    }

    @FXML
    public void goBack() {
        listingController.switchToBrowse();
    }

    @Override
    public void update() {
        reset();
        Arrays.fill(this.formData,"");
    }
}
