package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.View.HyroScene;
import edu.tda367.View.scenes.ListingSettingsView;
import edu.tda367.View.scenes.SingleListingView;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.*;

public class SceneHandler {
    private final Stage root;
    private Scene scene;
    private final Map<String, HyroScene> scenes = new HashMap<>();
    public SceneHandler(Stage root)
    {
        this.root = root;
    }

    public void switchTo(String newSceneName) {
        HyroScene newScene = scenes.get(newSceneName.toLowerCase());
        switchTo(newScene);
    }

    public boolean switchTo(HyroScene newScene) {
        if(newScene == null) {
            return false;
        }
        switchScenes(newScene);
        return true;
    }

    public void switchToListingView(Listing listing) throws IOException {
        SingleListingView view = new SingleListingView(new SingleListingController(this), listing.getUserId(), listing.getProduct().getProdName(), listing.getPrice(), listing.getCategoryName(), listing.getProduct().getDescription(), listing.getListingId(), listing.getFileName());
        root.setScene(view.getHyroScene());
        centerOnScreen();
        root.show();
    }

    public void switchToListingSettings(RentingItemEntry entry) throws IOException {
        ListingSettingsView view = new ListingSettingsView(new ListingSettingsController(this,entry.getListing()));
        root.setScene(view.getHyroScene());
        view.update();
        centerOnScreen();
        root.show();
    }

    public void switchScenes(HyroScene newScene) {
        root.setScene(newScene.getHyroScene());
        newScene.update();
        centerOnScreen();
        root.show();
    }

    public void addScene(HyroScene scene, String name) {
        this.scenes.put(name.toLowerCase(), scene);
    }

    private void centerOnScreen() {
        root.centerOnScreen();
    }

    private Scene getScene()
    {
        return this.scene;
    }

}
