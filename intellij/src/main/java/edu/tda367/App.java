package edu.tda367;

import edu.tda367.Controllers.CreateAccController;
import edu.tda367.Controllers.CreateListingController;
import edu.tda367.Controllers.MyAccController;
import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.HyroSceneFactory;
import edu.tda367.View.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private SceneHandler sceneHandler;
    private static ListingHandler lHandler;
    private static BookingHandler bHandler;
    @Override
    public void start(Stage stage) throws IOException {
        lHandler = ListingHandler.getInstance();
        bHandler = BookingHandler.getInstance();
        sceneHandler = new SceneHandler(stage);
        sceneHandler.addScene(HyroSceneFactory.browseScene(sceneHandler), "browse");
        sceneHandler.addScene(HyroSceneFactory.loginScene(sceneHandler), "login");
        CreateListingController lcCtrl = new CreateListingController(sceneHandler);
        sceneHandler.addScene(HyroSceneFactory.createListingSettingsScene(sceneHandler, lcCtrl),"createlisting");
        CreateAccController caCtrl = new CreateAccController(sceneHandler);
        MyAccController myAccCtrl = new MyAccController(sceneHandler);
        sceneHandler.addScene(HyroSceneFactory.accountViewScene(sceneHandler, myAccCtrl), "myaccount");
        sceneHandler.addScene(HyroSceneFactory.accountViewScene(sceneHandler, caCtrl), "createaccount");
        sceneHandler.addScene(HyroSceneFactory.rentingPage(sceneHandler), "rentingpage");
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
        Runtime.getRuntime().addShutdownHook(new Thread(() -> bHandler.writeBookings()));
    }

}
