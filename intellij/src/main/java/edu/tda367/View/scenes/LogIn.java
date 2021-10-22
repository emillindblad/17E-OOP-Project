package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.LogInController;
import edu.tda367.View.HyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogIn extends AnchorPane implements HyroScene {
    private final Scene scene;
    private final LogInController liController;

    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Button logInButton;
    @FXML private Label infoLabel;
    @FXML private Button createAccountButton;

    public LogIn(LogInController controller) throws IOException {
        FXMLLoader loader = App.loadFXML("login");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        liController = controller;

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                logInAttempt();
            }
        });
    }

    /**
     * Getter for scene. From interface hyroScene
     * @return this.scene
     */
    @Override
    public Scene getHyroScene() {
        return this.scene;
    }

    @Override
    public void update() {
        passwordField.clear();
        infoLabel.setText("");
    }


    /**
     * Attempts to log in using the credentials entered in the textfields.
     * If successful, UserHandler will now contain the logged in user, and scene will be switched to store home.
     * If unsuccessful, a label will appear displaying so.
     */
    @FXML
    private void logInAttempt() {
        if (!liController.logInAttemptIsValid(userNameField.getText(), passwordField.getText())) {
            infoLabel.setText("Fel användarnamn eller lösenord");
        }
    }

    @FXML
    private void createNewAccount() {
        liController.switchToCreateAccount();
    }
}
