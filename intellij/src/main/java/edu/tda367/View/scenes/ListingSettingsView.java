package edu.tda367.View.scenes;

import edu.tda367.Controllers.CreateListingController;
import edu.tda367.Controllers.ListingViewController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ListingSettingsView extends AbstractHyroScene {
    private final ListingViewController controller;

    @FXML private TextField productName;
    @FXML private TextField productDesc;
    @FXML private TextField prodPrice;
    @FXML private Label errorMsg;
    //@FXML private DatePicker prodAvail;
    @FXML private ComboBox<String> categoriesDropdown;
    @FXML private Label selectedFileName;
    @FXML private Button primaryButton;

    private File selectedFile;

    private String[] formData = new String[5];

    public ListingSettingsView(SceneHandler handler, ListingViewController controller) throws IOException {
        super(controller.getFXMLName(),handler);
        this.controller = controller;
        this.categoriesDropdown.getItems().setAll(loadCategories());
    }

    private ArrayList<String> loadCategories() {
        return controller.getCategoryNames();
    }

    private String[] getFormInput() { //TODO Get Availability
        return new String[]{productName.getText(),productDesc.getText(),prodPrice.getText(),categoriesDropdown.getSelectionModel().getSelectedItem(),selectedFile.getName()};
    }

    private void reset() {
        this.errorMsg.setText("");
        this.categoriesDropdown.setValue(null);
    }

    @FXML
    public void uploadFile() {
       FileChooser fileChooser = new FileChooser();
       File selectedFile = fileChooser.showOpenDialog(this.getHyroScene().getWindow());
       this.selectedFile = selectedFile;
       selectedFileName.setText(selectedFile.getName());
    }

    @FXML
    public void doneButtonAction() {
        this.formData = getFormInput();
        String path = "src/main/resources/edu/tda367/images/";
        errorMsg.setText(controller.doneButton(formData, this.selectedFile, path));
    }

    @FXML
    public void goBack() {
        controller.goBack();
    }

    @Override
    public void update() {
        controller.update(productName, productDesc, prodPrice);
        reset();
        Arrays.fill(this.formData,"");
    }
}
