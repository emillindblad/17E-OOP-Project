package edu.tda367.Controllers;

import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

public class MyAccountController implements Controller {

    private final SceneHandler handler;
    private final UserHandler uHandler;

    public MyAccountController(SceneHandler handler) {
        this.handler = handler;
        this.uHandler = UserHandler.getInstance();
    }

    public void saveUserInfo(String firstname, String lastName, String streetName, String city,
                                String zipCode, String country, String password, String phoneNumber, String bankAccount) {

    }


}

