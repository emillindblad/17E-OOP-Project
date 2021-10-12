package edu.tda367.Controllers;

import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

/**
 * Controller class for account creation view
 */
public class LogInController implements Controller {
    private final SceneHandler handler;

    /**
     * Contructor
     * @param handler SceneHandler containing "createAccount"-scene
     */
    public LogInController(SceneHandler handler) {
        this.handler = handler;
    }

    /**
     * Centers window on screen and switches to browse view
     */
    public void switchToBrowse() {
        handler.switchTo("browse");
        handler.centerOnScreen();
    }

    /**
     * Checks if username and password match database
     * returns true and switches to browse if login is valid
     * else returns false
     * @param uName username for login attempt
     * @param pWord password for login attempt
     * @return true if login valid, else false
     */
    public boolean logInAttemptIsValid(String uName, String pWord) {
        if (UserHandler.getInstance().logIn(uName, pWord)) {
            switchToBrowse();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Centers window on screen and switches to account creation view
     */
    public void switchToCreateAccount() {
        handler.switchTo("createaccount");
        handler.centerOnScreen();
    }
}
