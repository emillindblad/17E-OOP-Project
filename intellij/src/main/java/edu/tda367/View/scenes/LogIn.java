package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogIn extends AnchorPane implements hyroScene {
    private final Scene scene;
    private final SceneHandler handler;

    public LogIn(SceneHandler handler) throws IOException {
        FXMLLoader loader = App.loadFXML("login");
        System.out.println("here");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.handler = handler;
    }

    public Scene getHyroScene() {
        return this.scene;
    }
    @FXML
    public void switchToSecondary() {
        handler.switchTo("secondary");
    }
}
