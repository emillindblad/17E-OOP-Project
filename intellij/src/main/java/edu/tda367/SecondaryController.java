package edu.tda367;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        Platform.exit();
    }
}