package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogIn extends AnchorPane implements hyroScene {
    private final Scene scene;
    private final SceneHandler handler; //handler used to switch scenes

    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    @FXML private Button logInButton;
    @FXML private Label infoLabel;

    public LogIn(SceneHandler handler) throws IOException {
        FXMLLoader loader = App.loadFXML("login");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.handler = handler;
    }

    /**
     * Getter for scene. From interface hyroScene
     * @return this.scene
     */
    @Override
    public Scene getHyroScene() {
        return this.scene;
    }

    /**
     * Switches current scene to store home.
     * Makes sure the home screen is centered on monitor.
     */
    @FXML
    private void switchToHome() {
       handler.switchTo("home");
        handler.centerOnScreen();
    }

    @FXML
    private void switchToBrowse() {
       handler.switchTo("browse");
        handler.centerOnScreen();
    }

    /**
     * Attempts to log in using the credentials entered in the textfields.
     * If successful, UserHandler will now contain the logged in user, and scene will be switched to store home.
     * If unsuccessful, a label will appear displaying so.
     */
    @FXML
    private void logInAttempt() {
        if (UserHandler.getInstance().logIn(userNameField.getText(), passwordField.getText())) {
            switchToBrowse();
        } else {
            infoLabel.setText("Fel användarnamn eller lösenord");
        }
    }
}
