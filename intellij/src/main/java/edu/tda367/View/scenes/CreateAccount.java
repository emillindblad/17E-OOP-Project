package edu.tda367.View.scenes;

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
    @FXML private TextField adressField;
    @FXML private TextField zipField;
    @FXML private TextField cityField;
    @FXML private TextField countryField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPasswordField;
    @FXML private Button createAccountButton;
    @FXML private Button backButton;
    @FXML private Label infoLabel;

    public CreateAccount(String fxmlName, SceneHandler handler) throws IOException {
        super(fxmlName, handler);
    }

    @FXML
    private void createAccount() {

    }

    @FXML
    private void backToLogIn() {

    }
}
