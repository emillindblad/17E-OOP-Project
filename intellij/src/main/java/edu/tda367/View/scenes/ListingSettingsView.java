package edu.tda367.View.scenes;

import edu.tda367.Controllers.ListingViewController;
import edu.tda367.Controllers.SceneHandler;
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

    public ListingSettingsView(ListingViewController controller) throws IOException {
        super(controller.getFXMLName());
        this.controller = controller;
        this.categoriesDropdown.getItems().setAll(loadCategories());
    }

    private ArrayList<String> loadCategories() {
        return controller.getCategoryNames();
    }

    private String[] getFormInput() { //TODO Get Availability
        String file = "No image supplied";
        if (this.selectedFile!=null) {
            file = selectedFile.getName();
        }
        return new String[]{productName.getText(),productDesc.getText(),prodPrice.getText(),categoriesDropdown.getSelectionModel().getSelectedItem(),file};
    }

    /**
     * Uploads file from computer.
     * sets selectedFile to file uploaded
     */
    @FXML
    private void uploadFile() {
       FileChooser fileChooser = new FileChooser();
       File selectedFile = fileChooser.showOpenDialog(this.getHyroScene().getWindow());
        if (selectedFile!=null) {
            this.selectedFile = selectedFile;
            this.selectedFileName.setText(selectedFile.getName());
        }
    }

    /**
     * Will either attempt to create or edit listing depending on controller
     */
    @FXML
    private void doneButtonAction() {
        this.formData = getFormInput();
        String path = "src/main/resources/edu/tda367/images/";
        errorMsg.setText(controller.doneButton(formData, this.selectedFile, path));
    }

    @FXML
    private void secondButtonAction() {
        controller.secondAction();
    }

    @FXML
    public void goBack() {
        controller.goBack();
    }

    @Override
    public void update() {
        controller.update(productName, productDesc, prodPrice, categoriesDropdown);
        this.errorMsg.setText("");
        this.selectedFile = null;
        this.selectedFileName.setText("");
        Arrays.fill(this.formData,"");
    }
}
