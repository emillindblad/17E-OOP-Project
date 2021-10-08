package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.AccountViewController;
import edu.tda367.Controllers.MyAccountController;
import edu.tda367.Model.InputChecker;
import edu.tda367.Model.UserPackage.User;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class AccountView extends AbstractHyroScene {

    private AccountViewController controller;
    private final ArrayList<TextField> editableFields;
    // private final UserHandler userHandler;

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField streetName;
    @FXML private TextField zipCode;
    @FXML private TextField city;
    @FXML private TextField country;
    @FXML private TextField userName;
    @FXML private TextField bankAccount;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private TextField phoneNumber;
    @FXML private Button done;
    @FXML private ImageView baackButton;
    @FXML private Label info;

    public AccountView(SceneHandler handler, String fxmlName, AccountViewController controller) throws IOException {
        super(fxmlName, handler);
        this.controller = controller;
        editableFields = new ArrayList<>();
        populateFieldList();
    }

    //put all methods that test input in a list to run when changes are saved, as well as list of all fields
    private void populateFieldList() {
        editableFields.add(firstName);//0
        editableFields.add(lastName);//1
        editableFields.add(streetName);//2
        editableFields.add(zipCode);//3
        editableFields.add(city);//4
        editableFields.add(country);//5
        editableFields.add(password);//6
        editableFields.add(confirmPassword);//7
        editableFields.add(phoneNumber);//8
        editableFields.add(bankAccount);//9
        editableFields.add(userName); //10
    }

    private void setTextFields () {
        User user = UserHandler.getInstance().getLoggedInUser();
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        streetName.setText(user.getUserAdress().getStreetName());
        zipCode.setText(user.getUserAdress().getZipCode());
        city.setText(user.getUserAdress().getCity());
        country.setText(user.getUserAdress().getCountry());
        userName.setText(user.getUserName());
        phoneNumber.setText(user.getPhoneNumber());
        bankAccount.setText(user.getBankAccount());
        password.setText(user.getPassword());
        confirmPassword.setText(user.getPassword());
    }

    @FXML
    public void goBack() {
        controller.goBack();
    }

    @Override
    public Scene getHyroScene() {
        //setTextFields();
        return this.scene;
    }

    /**
     * Updates textfields in view.
     */
    @Override
    public void update() {
        controller.update(editableFields);
    }

    /**
     * Method called when button is pressed
     */
    @FXML
    public void changeButton () {
        controller.doneButtonAction(editableFields, info);
    }

}
