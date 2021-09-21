package edu.tda367;

import edu.tda367.Controllers.ListingController;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import edu.tda367.View.scenes.Home;
import edu.tda367.View.scenes.LogIn;
import edu.tda367.View.scenes.Secondary;

import java.io.IOException;

public final class HyroFactory {
    public static final hyroScene homeScene(SceneHandler handler) throws IOException {
        return new Home(handler);
    }

    public static final hyroScene secondaryScene(SceneHandler handler) throws IOException {
        return new Secondary(handler);
    }

    public static final hyroScene loginScene(SceneHandler director) throws IOException {
        return new LogIn(director);
    }
}