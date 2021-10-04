package edu.tda367.Controllers;

import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

/**
 * Controller class for account creation
 * Contaions method calls to model
 */
public class CreateAccountController {

    /**
     * Used to change scenes.
     * Set in constructor.
     */
    private final SceneHandler handler;

    /**
     * Constructor
     * @param handler used to change scenes
     */
    public CreateAccountController(SceneHandler handler) {
        this.handler = handler;
    }

    /**
     * Attempts to create an account based on parameters.
     * Checks if fields are correctly entered.
     * If yes, creates account.
     * If no, returns what the problem is through an enum.
     * @param firstName
     * @param lastName
     * @param userName
     * @param passWord
     * @param repeatPassWord
     * @param phoneNumber
     * @param bankAccount
     * @param address
     * @param zipCode
     * @param city
     * @param country
     * @return status of account creation, either success or what went wrong
     */
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

        if (!isValidZip(zipCode)) {
            return AccountCreationStatus.ZIPCODE_ERROR;
        }

        UserHandler.getInstance().createUser(firstName, lastName, phoneNumber, userName, passWord, bankAccount, zipCode, address, city, country);

        return AccountCreationStatus.SUCCESS;
    }

    /**
     * Returns to LogIn view
     */
    public void backToLogIn() {
        handler.switchTo("login");
        handler.centerOnScreen();
    }

    /**
     * Simple check to see if zip code is valid.
     * @param zip code to be checked
     * @return true if valid zip code.
     */
    private boolean isValidZip(String zip) {
        try {
            int i = Integer.parseInt(zip);
        } catch (NumberFormatException e) {
            return false;
        }

        return zip.length() == 5;
    }
}
