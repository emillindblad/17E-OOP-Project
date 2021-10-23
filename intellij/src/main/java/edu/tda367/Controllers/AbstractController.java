package edu.tda367.Controllers;

public abstract class AbstractController implements Controller {

    protected final SceneHandler handler;


    protected AbstractController(SceneHandler handler) {
        this.handler = handler;
    }

    @Override
    public void switchTo(String name) {
        handler.switchTo(name);
    }
}
