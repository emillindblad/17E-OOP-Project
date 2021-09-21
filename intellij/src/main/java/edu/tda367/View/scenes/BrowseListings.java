package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.View.SceneHandler;

import java.io.IOException;

public class BrowseListings extends AbstractHyroScene {
    BrowseController browseController;

    protected BrowseListings(String fxmlName, SceneHandler handler) throws IOException {
        super(fxmlName, handler);
        browseController = new BrowseController();
    }
}
