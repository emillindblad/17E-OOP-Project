module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens edu.tda367 to javafx.fxml;
    opens edu.tda367.UserPackage to com.google.gson;
    opens edu.tda367.Listing to com.google.gson;

    opens edu.tda367.View.scenes to javafx.fxml;
    exports edu.tda367;
}