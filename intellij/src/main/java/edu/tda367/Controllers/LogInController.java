package edu.tda367.Controllers;

import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

public class LogInController implements Controller {
    private final SceneHandler handler;

    public LogInController(SceneHandler handler) {
        this.handler = handler;
    }

    public void switchToBrowse() {
        handler.switchTo("browse");
        handler.centerOnScreen();
    }

    public boolean logInAttemptIsValid(String uName, String pWord) {
        if (UserHandler.getInstance().logIn(uName, pWord)) {
            switchToBrowse();
            return true;
        } else {
            return false;
        }
    }

    public void switchToCreateAccount() {
        handler.switchTo("createAccount");
        handler.centerOnScreen();
    }
}
