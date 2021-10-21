package edu.tda367.Controllers;

import edu.tda367.Model.UserPackage.UserHandler;

/**
 * Controller class for account creation view
 */
public class LogInController extends AbstractController {

    /**
     * Contructor
     * @param handler SceneHandler containing "createAccount"-scene
     */
    public LogInController(SceneHandler handler) {
        super(handler);
    }

    /**
     * Centers window on screen and switches to browse view
     */
    public void switchToBrowse() {
        handler.switchTo("browse");
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
    }
}
