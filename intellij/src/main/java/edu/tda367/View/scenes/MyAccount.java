package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MyAccount extends AnchorPane implements hyroScene {

    private final Scene scene;
    private final SceneHandler handler;

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField
    @FXML private TextField
    @FXML private TextField
    @FXML private TextField
    @FXML private TextField
    @FXML private TextField
    @FXML private PasswordField
    @FXML private PasswordField
    @FXML private Button



    public MyAccount(SceneHandler handler) throws IOException {
        FXMLLoader loader = App.loadFXML("myAccount");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.handler = handler;
    }

    @Override
    public Scene getHyroScene() {
        return this.scene;
    }
}
