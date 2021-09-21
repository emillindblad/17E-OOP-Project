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
        createDummyUsers(); //can be removed, no real functionality
    }

    /**
     * Dummy method for testing before users are added to database
     */
    private void createDummyUsers() {
        UserHandler.getInstance().createUser("a","b","1","a","b","1");
        UserHandler.getInstance().createUser("q","w","2","q","w","2");
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
     */
    @FXML
    private void switchToHome() {
        handler.switchTo("home");
    }

    /**
     * Attempts to log in using the credentials entered in the textfields.
     * If successful, UserHandler will now contain the logged in user, and scene will be switched to store home.
     * If unsuccessful, a label will appear displaying so.
     */
    @FXML
    private void logInAttempt() {
        if (UserHandler.getInstance().logIn(userNameField.getText(), passwordField.getText())) {
            switchToHome();
        } else {
            infoLabel.setText("Fel namn eller lösen");
        }
    }
}
