package edu.tda367.View.scenes;

import edu.tda367.Controllers.CreateAccountController;
import edu.tda367.Controllers.AccountCreationStatus;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateAccount extends AbstractHyroScene {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField userNameField;
    @FXML private TextField phoneField;
    @FXML private TextField bankField;
    @FXML private TextField addressField;
    @FXML private TextField zipField;
    @FXML private TextField cityField;
    @FXML private TextField countryField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPasswordField;
    @FXML private Button createAccountButton;
    @FXML private Button backButton;
    @FXML private Label infoLabel;
    private final CreateAccountController caController;

    public CreateAccount(String fxmlName, SceneHandler handler) throws IOException {
        super(fxmlName, handler);
        caController = new CreateAccountController(handler);
    }

    @FXML
    private void createAccount() {
        switch (caController.createAccountAttempt(
                firstNameField.getText(),
                lastNameField.getText(),
                userNameField.getText(),
                passwordField.getText(),
                repeatPasswordField.getText(),
                phoneField.getText(),
                bankField.getText(),
                addressField.getText(),
                zipField.getText(),
                cityField.getText(),
                countryField.getText()
        )) {
            case PASSWORD_MISSMATCH:
                infoLabel.setText("Lösenorden matchar inte!");
                break;
            case EMPTY_FIELD:
                infoLabel.setText("Fyll i alla fält först!");
                break;
            case ZIPCODE_ERROR:
                infoLabel.setText("Postkoden ska bara vara siffror, inga mellanslag");
                break;
            case SUCCESS:
                infoLabel.setText("Konto skapat!");
                break;
        }
    }

    @FXML
    private void backToLogIn() {
        caController.backToLogIn();
    }
}
