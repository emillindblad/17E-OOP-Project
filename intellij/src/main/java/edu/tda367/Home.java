
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class Home extends AnchorPane {
    private final Scene scene;
    private final SceneHandler handler;

    @FXML
    private Label testingLabel;

    public Home(SceneDirector director) {
        loadfxml = new FXMLLoader(getClass().getResource("primary"));
        loadfxml.setController(this);
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        this.director = director;
    }
}