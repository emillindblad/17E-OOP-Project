package edu.tda367.Controllers;

import edu.tda367.Model.InputChecker;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Controller for myAccount view
 */
public class MyAccountController implements Controller {

   // private final SceneHandler handler;
    private final UserHandler uHandler;


    public MyAccountController() {
        //this.handler = handler;
        this.uHandler = UserHandler.getInstance();
    }

    /**
     * Method to call when "change"-button is pressed.
     * @param editableFields Fields for userinformation, (eg name, adress)
     * @param changeButton The change-button
     */
    public void changeButton (ArrayList<TextField> editableFields, Button changeButton) {
        if (changeButton.getText().equals("Ändra")) {
            enableChanges(editableFields,changeButton);
        }
        else
            saveChanges(editableFields,changeButton);
    }

    private void enableChanges (ArrayList<TextField> editableFields, Button changeButton) {
        for(TextField field : editableFields){
            field.setEditable(true);
            changeButton.setText("Spara");
        }
    }

    private void saveChanges (ArrayList<TextField> editableFields, Button changeButton) {
        if(testInput(editableFields)) {
            for (TextField field : editableFields) {
                field.setEditable(false);
            }
            changeButton.setText("Ändra");
            saveUserInfo(editableFields);
        }
    }

    //TODO fix this shitty solution
    private void saveUserInfo(ArrayList<TextField> fields) {
        uHandler.setLoggedInUserFirstName(fields.get(0).getText());
        uHandler.setLoggedInUserLastName(fields.get(1).getText());
        uHandler.setLoggedInUserAdress(fields.get(2).getText(),fields.get(4).getText(),fields.get(3).getText(), fields.get(5).getText());
        uHandler.setLoggedInUserPasswword(fields.get(6).getText());
        uHandler.setLoggedInUserPhoneNumber(fields.get(8).getText());
        uHandler.setLoggedInUserBankAccount(fields.get(9).getText());
    }
    private boolean testInput(ArrayList<TextField> fields) {
        if( !zipCodeInput(fields.get(3)) || !bankAccountInput(fields.get(9)) || !firstNameInput(fields.get(0)) || !lastNameInput(fields.get(1)) || !countryInput(fields.get(5)) || !cityInput(fields.get(4)) || !passwordInput(fields.get(6), fields.get(7)) || !phoneInput(fields.get(8)))
            return false;
        return true;
    }

    private boolean zipCodeInput(TextField zipCode) {
        return numberFieldChecker(zipCode, 5);
    }

    private boolean bankAccountInput(TextField bankAccount) {
        if (InputChecker.checkForNumber(bankAccount.getText())){
            bankAccount.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        }
        else {
            bankAccount.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }

    private boolean firstNameInput (TextField firstName) {
        return textFieldChecker(firstName);
    }

    private boolean lastNameInput (TextField lastName) {
        return textFieldChecker(lastName);
    }

    private boolean countryInput (TextField country) {
        return textFieldChecker(country);
    }

    private boolean cityInput (TextField city) {
        return textFieldChecker(city);
    }

    private boolean passwordInput (TextField password, TextField confirmPassword) {
        System.out.println(password.getText());
        System.out.println(confirmPassword.getText());
        if (password.getText().equals(confirmPassword.getText())) {
            password.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            confirmPassword.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        } else
            password.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        confirmPassword.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        return false;
    }

    private boolean phoneInput (TextField phoneNumber) {
        if (!InputChecker.checkForLetter(phoneNumber.getText())){
            phoneNumber.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        }
        phoneNumber.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        return false;
    }


    private boolean numberFieldChecker (TextField field, int lenght) {
        if (InputChecker.checkForLength(field.getText(), lenght) && InputChecker.checkForNumber(field.getText())) {
            field.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        } else {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }

    private boolean textFieldChecker (TextField field) {
        if (InputChecker.checkForLetter(field.getText())) {
            field.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
            return true;
        } else {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }




}

