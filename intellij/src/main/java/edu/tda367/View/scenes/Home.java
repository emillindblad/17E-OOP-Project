package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.Controller;
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

    private final ListingController listingController;
    @FXML
    Label productName;

    @FXML
    Text price;

    public Home(SceneHandler handler) throws IOException {
        super("primary", handler);
        this.listingController = new ListingController(handler);
    }

    @FXML
    public void setPrice()
    {
        price.setText(String.valueOf(listingController.getPrice()));
    }
    @FXML
    public void switchToSecondary() {
        super.handler.switchTo("secondary");
    }

    @Override
    public void update() {

    }
}