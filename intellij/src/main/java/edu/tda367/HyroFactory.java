package edu.tda367;

import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import edu.tda367.View.scenes.Home;
import edu.tda367.View.scenes.LogIn;
import edu.tda367.View.scenes.Secondary;

import java.io.IOException;

public final class HyroFactory {
    public static final hyroScene homeScene(SceneHandler director) throws IOException {
        return new Home(director);
    }

    public static final hyroScene secondaryScene(SceneHandler director) throws IOException {
        return new Secondary(director);
    }

    public static final hyroScene loginScene(SceneHandler director) throws IOException {
        return new LogIn(director);
    }
}