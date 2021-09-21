package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.Controller;
import edu.tda367.Controllers.ListingController;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class AbstractHyroScene extends AnchorPane implements hyroScene {

    private final Scene scene;
    protected final SceneHandler handler;

    protected AbstractHyroScene(String fxmlName, SceneHandler handler) throws IOException {
        FXMLLoader loader = App.loadFXML(fxmlName);
        loader.setController(this);
        Parent root = loader.load();
        this.handler = handler;
        this.scene = new Scene(root);
    }

    @Override
    public Scene getHyroScene() {
        return this.scene;
    }
}
