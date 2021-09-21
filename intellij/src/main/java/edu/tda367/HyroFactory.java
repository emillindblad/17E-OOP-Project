package edu.tda367;

import edu.tda367.Controllers.ListingController;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import edu.tda367.View.scenes.Home;
import edu.tda367.View.scenes.Secondary;

import java.io.IOException;

public final class HyroFactory {
    public static final hyroScene homeScene(SceneHandler director, ListingController controller) throws IOException {
        return new Home(director, controller);
    }

    public static final hyroScene secondaryScene(SceneHandler director) throws IOException {
        return new Secondary(director);
    }
}