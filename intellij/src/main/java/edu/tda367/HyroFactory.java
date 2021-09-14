package edu.tda367;

import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;

import java.io.IOException;

public final class HyroFactory {
    public static final hyroScene homeScene(SceneHandler director) throws IOException {
        return new Home(director);
    }
}