import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.*;

public class SceneHandler {
    private final Stage root;
    private static Scene scene;
    private final Map<String, Scene> scenes = new HashMap<>();
    public SceneHandler(Stage root)
    {
        this.root = root;
        scene = new Scene(loadFXML("primary"));
        root.setScene(scene);
        stage.SetTitle("Home Page");
        stage.show();
    }

    public void switchTo(String name) {
        App.setRoot(name);
    }

    public void addScene(hyroScene scene, string name) {
        this.scenes.put(name.toLowerCase(), scene);
    }


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("Hyro");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private Scene getScene()
    {
        return scene;
    }
}