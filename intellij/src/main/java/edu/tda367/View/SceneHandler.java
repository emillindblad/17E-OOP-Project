package edu.tda367.View;

import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.Console;
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
        System.out.println(newSceneName);
    }

    public boolean switchTo(hyroScene newScene) {
        if(newScene == null) {
            return false;
        }
        switchScenes(newScene);
        return true;
    }

    public void switchScenes(hyroScene newScene) {
        root.setScene(newScene.getHyroScene());
        root.show();
    }

    public void addScene(hyroScene scene, String name) {
        this.scenes.put(name.toLowerCase(), scene);
    }



    private Scene getScene()
    {
        return this.scene;
    }
}