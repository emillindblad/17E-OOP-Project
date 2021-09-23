package edu.tda367.View.scenes;

import edu.tda367.Controllers.ListingController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateListingScene extends AbstractHyroScene {
    private final ListingController listingController;

    @FXML private TextField productName;
    @FXML private TextField productDesc;
    @FXML private TextField prodPrice;
    @FXML private TextField prodAvail;
    @FXML private ComboBox categoriesDropdown;

    public CreateListingScene(SceneHandler handler) throws IOException {
        super("createListing",handler);
        this.listingController = new ListingController();
    }

    @FXML
    public void createListing() {

    }
}
