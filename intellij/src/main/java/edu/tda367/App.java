package edu.tda367;

import edu.tda367.Controllers.AccountViewController;
import edu.tda367.Controllers.CreateAccController;
import edu.tda367.Controllers.MyAccController;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.HyroSceneFactory;
import edu.tda367.View.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private SceneHandler sceneHandler;
    private static ListingHandler lHandler;

    @Override
    public void start(Stage stage) throws IOException {
        lHandler = ListingHandler.getInstance();
        sceneHandler = new SceneHandler(stage);
        sceneHandler.addScene(HyroSceneFactory.homeScene(sceneHandler), "home");
        sceneHandler.addScene(HyroSceneFactory.secondaryScene(sceneHandler), "secondary");
        sceneHandler.addScene(HyroSceneFactory.browseScene(sceneHandler), "browse");
        sceneHandler.addScene(HyroSceneFactory.loginScene(sceneHandler), "login");
        sceneHandler.addScene(HyroSceneFactory.createListingScene(sceneHandler),"createlisting");
        CreateAccController caCtrl = new CreateAccController(sceneHandler);
        MyAccController myAccCtrl = new MyAccController(sceneHandler);
        sceneHandler.addScene(HyroSceneFactory.accountViewScene(sceneHandler, myAccCtrl), "myaccount");
        sceneHandler.addScene(HyroSceneFactory.accountViewScene(sceneHandler, caCtrl), "createaccount");
        sceneHandler.switchTo("login");
    }

    public static FXMLLoader loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> UserHandler.getInstance().writeUsers()));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> lHandler.writeListings()));
    }

}
