package edu.tda367.View;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.View.scenes.SingleListingView;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.*;

public class SceneHandler {
    private final Stage root;
    private Scene scene;
    private final Map<String, hyroScene> scenes = new HashMap<>();
    public SceneHandler(Stage root)
    {
        this.root = root;
    }

    public void switchTo(String newSceneName) {
        hyroScene newScene = scenes.get(newSceneName.toLowerCase());
        switchTo(newScene);
    }

    public boolean switchTo(hyroScene newScene) {
        if(newScene == null) {
            return false;
        }
        switchScenes(newScene);
        return true;
    }

    public void switchToListingView(Listing listing) throws IOException {
        SingleListingView view = new SingleListingView(this, listing.getUserId(), listing.getProduct().getProdName(), listing.getPrice(), "Sample text", "Lorem ipsmum dolor asdbsolmannen", listing.getListingId(), listing.getFileName());
        root.setScene(view.getHyroScene());
        root.show();

    }

    public void switchScenes(hyroScene newScene) {
        root.setScene(newScene.getHyroScene());
        newScene.update();
        root.show();
    }

    public void addScene(hyroScene scene, String name) {
        this.scenes.put(name.toLowerCase(), scene);
    }

    public void setLocationOnScreen(double x, double y) {
        root.setX(x);
        root.setY(y);
    }

    public void centerOnScreen() {
        root.centerOnScreen();
    }

    private Scene getScene()
    {
        return this.scene;
    }

}