package edu.tda367;

import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;

public final class HyroFactory {
    public final hyroScene homeScene(SceneHandler director) {
        return new Home(director);
    }
}