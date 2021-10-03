package edu.tda367.View.scenes;

import edu.tda367.App;
import edu.tda367.Controllers.MyAccountController;
import edu.tda367.InputChecker;
import edu.tda367.UserPackage.User;
import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.hyroScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MyAccount extends AbstractHyroScene {

    //TODO add controller and refractor to it!
    private MyAccountController controller;
    private final ArrayList<TextField> editableFields;
    private final UserHandler userHandler;

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
    @FXML private Button change;



    public MyAccount(SceneHandler handler) throws IOException {
        super("myAccount", handler);
        //FXMLLoader loader = App.loadFXML("myAccount");
        //loader.setController(this);
        //Parent root = loader.load();
        //this.scene = new Scene(root);
        this.userHandler = UserHandler.getInstance();
        controller = new MyAccountController(handler);
        editableFields = new ArrayList<>();
        populateFieldList();
        change.setText("Ändra");
        //setTextFields();
    }

    //put all methods that test input in a list to run when changes are saved, as well as list of all fields
    private void populateFieldList() {
        editableFields.add(firstName);
        editableFields.add(lastName);
        editableFields.add(streetName);
        editableFields.add(zipCode);
        editableFields.add(city);
        editableFields.add(country);
        editableFields.add(bankAccount);
        editableFields.add(password);
        editableFields.add(confirmPassword);
        editableFields.add(phoneNumber);
    }

    public void setTextFields () {
        User user = userHandler.getLoggedInUser();
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
    @Override
    public Scene getHyroScene() {
        setTextFields();
        return this.scene;
    }


    @Override
    public void update() {
        setTextFields();
    }

    @FXML
    public void changeButton () {
        controller.changeButton(editableFields,change);
    }

}
