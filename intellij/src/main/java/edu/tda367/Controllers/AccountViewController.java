package edu.tda367.Controllers;

import edu.tda367.Model.InputChecker;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public abstract class AccountViewController {

    protected final SceneHandler handler;

    protected AccountViewController(SceneHandler handler) {
        this.handler = handler;
    }

    public abstract void doneButtonAction(ArrayList<TextField> fields, Label info);

    public abstract void goBack();

    public abstract void update(ArrayList<TextField> fields);

    protected boolean testInput(ArrayList<TextField> fields) {
        if( !zipCodeInput(fields.get(3)) || !bankAccountInput(fields.get(9)) || !firstNameInput(fields.get(0)) || !lastNameInput(fields.get(1)) || !countryInput(fields.get(5)) || !cityInput(fields.get(4)) || !passwordInput(fields.get(6), fields.get(7)) || !phoneInput(fields.get(8)))
            return false;
        return true;
    }

    protected boolean zipCodeInput(TextField zipCode) {
        return numberFieldChecker(zipCode, 5);
    }

    protected boolean bankAccountInput(TextField bankAccount) {
        if (InputChecker.checkForNumber(bankAccount.getText())){
            bankAccount.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        }
        else {
            bankAccount.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }

    protected boolean firstNameInput (TextField firstName) {
        return textFieldChecker(firstName);
    }

    protected boolean lastNameInput (TextField lastName) {
        return textFieldChecker(lastName);
    }

    protected boolean countryInput (TextField country) {
        return textFieldChecker(country);
    }

    protected boolean cityInput (TextField city) {
        return textFieldChecker(city);
    }

    protected boolean passwordInput (TextField password, TextField confirmPassword) {
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

    protected boolean phoneInput (TextField phoneNumber) {
        if (!InputChecker.checkForLetter(phoneNumber.getText())){
            phoneNumber.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        }
        phoneNumber.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        return false;
    }


    protected boolean numberFieldChecker (TextField field, int lenght) {
        if (InputChecker.checkForLength(field.getText(), lenght) && InputChecker.checkForNumber(field.getText())) {
            field.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        } else {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }

    protected boolean textFieldChecker (TextField field) {
        if (InputChecker.checkForLetter(field.getText())) {
            field.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
            return true;
        } else {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }
}
