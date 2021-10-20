package edu.tda367.Controllers;

import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MyAccController extends AccountViewController{

    private final UserHandler uHandler = UserHandler.getInstance();

    public MyAccController(SceneHandler handler) {
        super(handler);
    }

    /**
     * called when button on scene is pressed
     * Attempts to save changes to user settings
     * Feedback will be shown depending on success or what went wrong
     * @param fields
     * @param info
     */
    @Override
    public void doneButtonAction(ArrayList<TextField> fields, Label info) {
        if (testInput(fields)) {
            info.setText("Uppgifter sparade");
            saveUserInfo(fields);
        } else {
            info.setText("Alla fält är inte korrekt ifyllda");
        }
    }

    private void saveUserInfo(ArrayList<TextField> fields) {
        uHandler.setLoggedInUserFirstName(fields.get(0).getText());
        uHandler.setLoggedInUserLastName(fields.get(1).getText());
        uHandler.setLoggedInUserAdress(fields.get(2).getText(),fields.get(4).getText(),fields.get(3).getText(), fields.get(5).getText());
        uHandler.setLoggedInUserPasswword(fields.get(6).getText());
        uHandler.setLoggedInUserPhoneNumber(fields.get(8).getText());
        uHandler.setLoggedInUserBankAccount(fields.get(9).getText());
        uHandler.setLoggedInUserUserName(fields.get(10).getText());
    }

    /**
     * Goes back to browse page
     */
    @Override
    public void goBack() {
        handler.switchTo("browse");
    }

    /**
     * sets fields to show user info
     * @param fields textfields to be set
     */
    @Override
    public void update(ArrayList<TextField> fields) {
        fields.get(0).setText(uHandler.getLoggedInUser().getFirstName());
        fields.get(1).setText(uHandler.getLoggedInUser().getLastName());
        fields.get(2).setText(uHandler.getLoggedInUser().getUserAdress().getStreetName());
        fields.get(3).setText(uHandler.getLoggedInUser().getUserAdress().getZipCode());
        fields.get(4).setText(uHandler.getLoggedInUser().getUserAdress().getCity());
        fields.get(5).setText(uHandler.getLoggedInUser().getUserAdress().getCountry());
        fields.get(6).setText(uHandler.getLoggedInUser().getPassword());
        fields.get(8).setText(uHandler.getLoggedInUser().getPhoneNumber());
        fields.get(9).setText(uHandler.getLoggedInUser().getBankAccount());
        fields.get(10).setText(uHandler.getLoggedInUser().getUserName());
    }

    /**
     * Getter for name of FXML file this controller is connected to
     * @return Name of FXML file
     */
    @Override
    public String getFXMLname() {
        return "myaccount";
    }
}
