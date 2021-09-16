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
    private final SceneHandler handler;

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
        //dummy users for login test
        UserHandler.getInstance().createUser("a","b","1","a","b","1");
        UserHandler.getInstance().createUser("q","w","2","q","w","2");
    }

    public Scene getHyroScene() {
        return this.scene;
    }

    @FXML
    public void switchToHome() {
        handler.switchTo("home");
    }

    @FXML
    public void logInAttempt() {
        if (UserHandler.getInstance().logIn(userNameField.getText(), passwordField.getText())) {
            switchToHome();
        } else {
            infoLabel.setText("Fel namn eller lösen");
        }
    }
}
