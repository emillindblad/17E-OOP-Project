package edu.tda367;

import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Home extends AnchorPane implements hyroScene {
    private final Scene scene;
    private final SceneHandler handler;

    @FXML
    private Label testingLabel;

    public Home(SceneHandler handler) throws IOException {
        FXMLLoader loadfxml = new FXMLLoader(getClass().getResource("../../resources/edu.tda367/primary.fxml"));
        loadfxml.setController(this);
        Parent root = loadfxml.load();
        this.scene = new Scene(root);
        this.handler = handler;
    }
}