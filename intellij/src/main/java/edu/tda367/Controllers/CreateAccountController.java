package edu.tda367.Controllers;

import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

public class CreateAccountController {

    private final SceneHandler handler;

    public CreateAccountController(SceneHandler handler) {
        this.handler = handler;
    }

    public AccountCreationStatus createAccountAttempt(String firstName,
                                     String lastName,
                                     String userName,
                                     String passWord,
                                     String repeatPassWord,
                                     String phoneNumber,
                                     String bankAccount,
                                     String address,
                                     String zipCode,
                                     String city,
                                     String country) {
        if (!passWord.equals(repeatPassWord)) {
            return AccountCreationStatus.PASSWORD_MISSMATCH;
        }

        if (firstName.strip().length() == 0 ||
                lastName.strip().length() == 0 ||
                userName.strip().length() == 0 ||
                passWord.strip().length() == 0 ||
                repeatPassWord.strip().length() == 0 ||
                phoneNumber.strip().length() == 0 ||
                bankAccount.strip().length() == 0 ||
                address.strip().length() == 0 ||
                zipCode.strip().length() == 0 ||
                city.strip().length() == 0 ||
                country.strip().length() == 0) {
            return AccountCreationStatus.EMPTY_FIELD;
        }

        return AccountCreationStatus.SUCCESS;


    }

    public void backToLogIn() {
        handler.switchTo("login");
    }
}
