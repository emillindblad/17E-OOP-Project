package edu.tda367.View.scenes;

import edu.tda367.App;
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

    private final List<TextField> editableFields;
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
        zipCode.setText(Integer.toString(user.getUserAdress().getZipCode()));
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
        if (change.getText().equals("Ändra")) {
            enableChanges();
        }
        else
            saveChanges();
    }

    public void enableChanges () {
        for(TextField field : editableFields){
            field.setEditable(true);
            change.setText("Spara");
        }
    }

    private void saveChanges () {
        if(testInput()) {
            for (TextField field : editableFields) {
                field.setEditable(false);
            }
            change.setText("Ändra");
            saveUserInfo();
        }
    }
    /*TODO make method in controller (saveUserInfo) that takes in the info checks it and saves it,
        or return what information that needs to be corrected. Could probably be done by having controller call all
        test  methods (in test input, and return the fields numbers of the areas that are wrong so view can change border color
        might even be possible to send the list of buttons to controller (pass by ref) and change immediately in the list*/

    /*TODO saveChanges -> controller.saveChanges (List Buttons) -> loop of buttons, button.get text, call handler to set value.
    *  -> uHandler.setFirstName(String name) -> returns boolean, success/fail, set color accordingly. */

    private void saveUserInfo() {
        userHandler.setLoggedInUserFirstName(firstName.getText());
        userHandler.setLoggedInUserLastName(lastName.getText());
        userHandler.setLoggedInUserAdress(streetName.getText(),city.getText(),Integer.parseInt(zipCode.getText()), country.getText());
        userHandler.setLoggedInUserPasswword(password.getText());
        userHandler.setLoggedInUserPhoneNumber(phoneNumber.getText());
        userHandler.setLoggedInUserBankAccount(bankAccount.getText());
    }
    private boolean testInput() {
        if( !zipCodeInput() || !bankAccountInput() || !firstNameInput() || !lastNameInput() || !countryInput() || !cityInput() || !passwordInput())
            return false;
        return true;
    }

    private boolean zipCodeInput() {
    return numberFieldChecker(zipCode, 5);
    }

    private boolean bankAccountInput() {
        if (InputChecker.checkForNumber(bankAccount.getText())){
            bankAccount.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        }
        else {
            bankAccount.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return false;
        }
    }

    private boolean firstNameInput () {
        return textFieldChecker(firstName);
    }

    private boolean lastNameInput () {
        return textFieldChecker(lastName);
    }

    private boolean countryInput () {
        return textFieldChecker(country);
    }

    private boolean cityInput () {
        return textFieldChecker(city);
    }

    private boolean passwordInput () {
        //System.out.println(password.getText());
        //System.out.println(confirmPassword.getText());
        if (password.getText().equals(confirmPassword.getText())) {
            password.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            confirmPassword.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
            return true;
        } else
        password.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        confirmPassword.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        return false;
    }


    private boolean numberFieldChecker (TextField field, int lenght) { //something wrong with the lenght check
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
