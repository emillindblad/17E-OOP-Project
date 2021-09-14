package edu.tda367;

import edu.tda367.View.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private SceneHandler sceneHandler;

    @Override
    public void start(Stage stage) throws IOException {
        sceneHandler = new SceneHandler(stage);
        sceneHandler.addScene(HyroFactory.homeScene(sceneHandler), "home");
    }

    public static void main(String[] args) {
        launch();
    }

}