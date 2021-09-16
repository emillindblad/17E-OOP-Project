module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.tda367 to javafx.fxml;
    opens edu.tda367.View.scenes to javafx.fxml;
    exports edu.tda367;
}