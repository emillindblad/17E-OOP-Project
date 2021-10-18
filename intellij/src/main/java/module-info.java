module edu.tda367 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.datatransfer;
    requires java.desktop;
    requires org.apache.commons.text;

    opens edu.tda367 to javafx.fxml;
    opens edu.tda367.Model.UserPackage to com.google.gson;
    opens edu.tda367.Model.Listing to com.google.gson;
    opens edu.tda367.Model.Booking to com.google.gson;

    opens edu.tda367.View.scenes to javafx.fxml;
    exports edu.tda367;
    exports edu.tda367.Model;
    opens edu.tda367.Model to javafx.fxml;
    exports edu.tda367.View;
    opens edu.tda367.View to javafx.fxml;
}