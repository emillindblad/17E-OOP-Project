package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.AbstractController;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class AbstractHyroScene extends AnchorPane implements hyroScene {

     final Scene scene;

    /**
     * Super constructor for all scenes. Loads the FXML file and creates a scene with the loaded fxml file
     * @param handler sceneHandler that is in charge of switching between scenes
     * @param fxmlName name of the fxml file that you want to load with the scene
     * @param controller
     */
    protected AbstractHyroScene(String fxmlName) throws IOException {
        FXMLLoader loader = App.loadFXML(fxmlName);
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
    }

    @Override
    public Scene getHyroScene() {
        return this.scene;
    }
}
