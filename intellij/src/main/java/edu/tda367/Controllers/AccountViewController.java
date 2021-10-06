package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public abstract class AccountViewController {

    protected final SceneHandler handler;

    protected AccountViewController(SceneHandler handler) {
        this.handler = handler;
    }

    public abstract void doneButtonAction(ArrayList<TextField> fields, Button doneButton);

    public abstract void goBack();

    public abstract void update(ArrayList<TextField> fields, Button doneButton);
}
