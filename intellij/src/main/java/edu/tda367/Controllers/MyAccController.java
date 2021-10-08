package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MyAccController extends AccountViewController{

    public MyAccController(SceneHandler handler) {
        super(handler);
    }

    @Override
    public void doneButtonAction(ArrayList<TextField> fields, Label info) {

    }

    @Override
    public void goBack() {
        handler.switchTo("browse");
    }

    @Override
    public void update(ArrayList<TextField> fields) {

    }
}
