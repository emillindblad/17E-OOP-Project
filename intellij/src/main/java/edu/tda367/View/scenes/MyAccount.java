package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.InputChecker;
import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyAccount extends AnchorPane implements hyroScene {

    private final Scene scene;
    private final SceneHandler handler;
    private final List<TextField> editableFields;
    private final UserHandler userHandler;
    private boolean correctInput;

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField streetName;
    @FXML private TextField zipCode;
    @FXML private TextField city;
    @FXML private TextField country;
    @FXML private TextField userName;
    @FXML private TextField bankAccount;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private Button change;



    public MyAccount(SceneHandler handler) throws IOException {
        FXMLLoader loader = App.loadFXML("myAccount");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.handler = handler;
        this.userHandler = UserHandler.getInstance();
        editableFields = new ArrayList<TextField>();
        populateFieldLists();
        correctInput = true;
    }

    private void populateFieldLists() {
        editableFields.add(firstName);
        editableFields.add(lastName);
        editableFields.add(streetName);
        editableFields.add(zipCode);
        editableFields.add(city);
        editableFields.add(country);
        editableFields.add(password);
        editableFields.add(confirmPassword);
        editableFields.add(bankAccount);

    }

    @Override
    public Scene getHyroScene() {
        return this.scene;
    }

    public void enableChanges () {
        for(TextField field : editableFields){
            field.setEditable(true);
        }
    }

    public void saveChanges () {
        for(TextField field : editableFields){
            field.setEditable(false);
        }
    }

    @FXML
    public boolean zipCodeInput() {
    return numberFieldChecker(zipCode, 5);
    }

    @FXML
    public boolean bankAccountInput() {
        if (InputChecker.checkForNumber(bankAccount.toString())){
            bankAccount.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
            return true;
        }
        else {
            bankAccount.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false;
        }
    }

    @FXML

    private boolean numberFieldChecker (TextField field, int lenght) {

        if (InputChecker.checkForLength(field.toString(), lenght) && InputChecker.checkForNumber(field.toString())) {
            field.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
            return true;
        } else {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false;
        }
    }

    private boolean textFieldChecker (TextField field) {
        if (InputChecker.checkForLetter(field.toString())) {
            field.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
            return true;
        } else {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false;
        }
    }


}
