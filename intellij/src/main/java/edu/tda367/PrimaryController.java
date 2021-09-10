package edu.tda367;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    private final SceneHandler handler;

    PrimaryController(SceneHandler handler)
    {
        this.handler = handler;
    }
    @FXML
    private void switchToSecondary() throws IOException {
        handler.switchTo("Primary");
        System.out.println("P.R.I.T. grill hyrd!");
    }
}
