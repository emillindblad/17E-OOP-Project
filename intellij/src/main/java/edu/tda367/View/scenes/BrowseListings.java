package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

public class BrowseListings extends AbstractHyroScene {
    BrowseController browseController;

    @FXML
    FlowPane listProducts;
    @FXML
    TextField searchField;

    /**
     * Constructs the BrowseListings Scene
     * @param handler sends the sceneHandler to super
     * lastly it runs updateList to make sure all content is up to date when scene is created.
     */
    public BrowseListings(SceneHandler handler) throws IOException {
        super("browse", handler);
        browseController = new BrowseController(handler);
        searchField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                search();
            }
        } );
        updateList();
    }

    @FXML
    private void logOut() {
        logOutDialog();
    }

    public void logOutDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekräfta Utloggning");
        String s = "Vill du logga ut?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            browseController.logOut();
            handler.switchTo("LogIn");
        }
    }

    // TODO fill method
    @FXML
    private void goToListings() {

    }

    @FXML
    private void switchToCreate() {
        handler.switchTo("createlisting");
        handler.centerOnScreen();
    }

    @FXML
    private void switchToUserSettings() {
        handler.switchTo("myaccount");
        handler.centerOnScreen();
    }


    /**
     * Fetches a list of available listings from the controller and adds them all to the FXML flowpane so that it gets displayed in the GUI
     */
    void updateList() {
        var listings = browseController.getAvailableListings();
        listProducts.getChildren().clear();
        for(int i = 0; i < listings.size(); i++)
        {
            listProducts.getChildren().add(new ListingItem(this.handler, this, listings.get(i).getPrice(), listings.get(i).getProduct().getProdName(), listings.get(i).getProduct().getCategoryName()));
        }
    }

    @FXML
    public void search() {
        browseController.search(searchField.getText());
        System.out.println("Searched");
        update();
        System.out.println("updated list");
    }

    /**
     * Method runs everytime scene is switched to so flowpane gets updated on scene switch.
     */
    @Override
    public void update() {
        updateList();
    }

    //CategoryButtons

    @FXML
    public void CatButtonPressed(ActionEvent event) {
        Button button = (Button) event.getSource();
        browseController.search(button.getText());
        update();
    }
}
