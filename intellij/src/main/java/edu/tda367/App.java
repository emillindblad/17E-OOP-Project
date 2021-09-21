package edu.tda367;

import edu.tda367.JSON.JSONWriter;
import edu.tda367.Listing.Category;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * JavaFX App
 */
public class App extends Application {

    private SceneHandler sceneHandler;

    @Override
    public void start(Stage stage) throws IOException {
        UserHandler uHandler = new UserHandler();
        uHandler.writeUsers();
        ListingHandler lHandler = new ListingHandler();
        lHandler.writeListings();
        sceneHandler = new SceneHandler(stage);
        sceneHandler.addScene(HyroFactory.homeScene(sceneHandler), "home");
        sceneHandler.addScene(HyroFactory.secondaryScene(sceneHandler), "secondary");
        sceneHandler.switchTo("home");
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

}