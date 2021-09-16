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
    private final PrimaryController primaryController;
    private final SecondaryController secondaryController;
    @FXML
    private Label testingLabel;

    public Home(SceneHandler handler) throws IOException {
        FXMLLoader loader = App.loadFXML("secondary");
        System.out.println("here");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.handler = handler;
        this.secondaryController = new SecondaryController();
        this.primaryController = new PrimaryController(handler);

    }

    @FXML
    public void switchToPrimary() {
        handler.switchTo("primary");
    }
}