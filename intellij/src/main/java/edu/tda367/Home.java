package edu.tda367;

import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Home extends AnchorPane implements hyroScene {
    private final Scene scene;
    private final SceneHandler handler;

    @FXML
    private Label testingLabel;

    public Home(SceneHandler handler) {
        loadfxml = new FXMLLoader(getClass().getResource("primary"));
        loadfxml.setController(this);
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        this.handler = handler;
    }
}