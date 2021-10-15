package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;

public class BrowseListings extends AbstractHyroScene {
    BrowseController browseController;

    @FXML
    GridPane listProducts;
    @FXML
    TextField searchField;

    @FXML
    public Button catButton1, catButton2, catButton3, catButton4, catButton5, catButton6,catButton7, catButton8;

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
        int index0 = 0;
        int index1 =0;
        listProducts.getChildren().clear();
        for(Listing l: listings) {
                listProducts.add(new ListingItem(this.handler, this, l.getPrice(), l.getProduct().getProdName(), l.getProduct().getCategoryName(), l.getListingId()), index0,index1 );

            index0++;
            if( index0 == 2){
                index1++;
                index0 = 0;
                }
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
    public void CatButton1Pressed() {
        browseController.search(catButton1.getText());
        update();
    }
    @FXML
    public void CatButton2Pressed() {
        browseController.search(catButton2.getText());
        update();
    }
    @FXML
    public void CatButton3Pressed() {
        browseController.search(catButton3.getText());
        update();
    }
    @FXML
    public void CatButton4Pressed() {
        browseController.search(catButton4.getText());
        update();
    }
    @FXML
    public void CatButton5Pressed() {
        browseController.search(catButton5.getText());
        update();
    }
    @FXML
    public void CatButton6Pressed() {
        browseController.search(catButton6.getText());
        update();
    }
    @FXML
    public void CatButton7Pressed() {
        browseController.search(catButton7.getText());
        update();
    }
    @FXML
    public void CatButton8Pressed() {
        browseController.search(catButton8.getText());
        update();
    }
}
