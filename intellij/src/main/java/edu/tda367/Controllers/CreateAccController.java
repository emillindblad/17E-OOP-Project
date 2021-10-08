package edu.tda367.Controllers;

import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CreateAccController extends AccountViewController{

    public CreateAccController(SceneHandler handler) {
        super(handler);
    }

    @Override
    public void doneButtonAction(ArrayList<TextField> fields, Label info) {
        if (testInput(fields)) {
            info.setText("Konto skapat!");
            UserHandler.getInstance().createUser(fields.get(0).getText(),
                                                fields.get(1).getText(),
                                                fields.get(8).getText(),
                                                fields.get(10).getText(),
                                                fields.get(6).getText(),
                                                fields.get(9).getText(),
                                                fields.get(3).getText(),
                                                fields.get(2).getText(),
                                                fields.get(4).getText(),
                                                fields.get(5).getText());
        } else {
            info.setText("Alla fält är inte korrekt ifyllda");
        }
    }

    @Override
    public void goBack() {
        handler.switchTo("login");
        handler.centerOnScreen();
    }

    @Override
    public void update(ArrayList<TextField> fields) {
        for (TextField t: fields) {
            t.setText("");
        }
    }

    @Override
    public String getFXMLname() {
        return "createaccount";
    }
}
