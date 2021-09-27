package edu.tda367.View.scenes;

import edu.tda367.Controllers.ListingController;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateListingScene extends AbstractHyroScene {
    //private final ListingController listingController;
    private final ListingHandler handler;

    @FXML private TextField productName;
    @FXML private TextField productDesc;
    @FXML private TextField prodPrice;
    @FXML private DatePicker prodAvail;
    @FXML private ComboBox<String> categoriesDropdown;

    public CreateListingScene(SceneHandler handler) throws IOException {
        super("createlisting",handler);
        this.handler = new ListingHandler();

        this.categoriesDropdown.getItems().setAll(loadCategories());
        System.out.println("Create Listing now");
    }

    public ArrayList<String> loadCategories() {
        return handler.getCategoryNames();
    }

    public void getFormInput() {
        System.out.println(productName.getText());
        System.out.println(productDesc.getText());
        System.out.println(prodPrice.getText());
        System.out.println(categoriesDropdown.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void createListing() {
        getFormInput();
        System.out.println("Created listing");
        //handler.createListing();
    }
}
