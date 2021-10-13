package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;

public class MyListingsController {

    private final SceneHandler handler;

    public MyListingsController(SceneHandler handler) {
        this.handler = handler;
    }

    public void goBack() {
        handler.switchTo("browse");
    }
}
