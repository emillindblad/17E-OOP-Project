package edu.tda367.Controllers;

import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

public class LogInController implements Controller {
    private final SceneHandler handler;

    public LogInController(SceneHandler handler) {
        this.handler = handler;
    }

    public void switchToHome() {
        handler.switchTo("home");
        handler.centerOnScreen();
    }

    public boolean logInAttemptIsValid(String uName, String pWord) {
        if (UserHandler.getInstance().logIn(uName, pWord)) {
            return true;
        } else {
            return false;
        }
    }
}
