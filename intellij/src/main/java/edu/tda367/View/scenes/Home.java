package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.ListingController;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Home extends AbstractHyroScene {
    private final Scene scene;
    private final SceneHandler handler;
    private final ListingController controller;

    @FXML
    Label productName;

    @FXML
    Text price;

    public Home(SceneHandler handler) throws IOException {
        super("primary", );
        FXMLLoader loader = App.loadFXML("primary");
        System.out.println("here");
        loader.setController(this);
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.handler = handler;
        this.controller = new ListingController();
        setPrice();
    }

    public Scene getHyroScene() {
        return this.scene;
    }

    @FXML
    public void setPrice()
    {
        price.setText(String.valueOf(controller.getPrice()));
    }
    @FXML
    public void switchToSecondary() {
        handler.switchTo("secondary");
    }
}