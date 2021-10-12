package edu.tda367.View.scenes;

import edu.tda367.Controllers.MyListingsController;
import edu.tda367.View.SceneHandler;

import java.io.IOException;

public class MyListings extends AbstractHyroScene {

    private final MyListingsController controller;

    public MyListings(SceneHandler handler) throws IOException {
        super("mylistings", handler);
        controller = new MyListingsController(handler);
    }

    @Override
    public void update() {

    }
}
