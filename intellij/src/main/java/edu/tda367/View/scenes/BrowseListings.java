package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.View.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
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


    @FXML
    private void switchToCreate() {
        handler.switchTo("createlisting");
    }

    @FXML
    private void switchToUserSettings() {
        handler.switchTo("myaccount");
    }

    @FXML
    private void goToListings() {
        handler.switchTo("rentingpage");
    }


    /**
     * Update list with an previously created set of Keys.
     * Also checks so all Keys the method gets are connected to a available listing.
     * @param keys set of keys to Listings
     */
    void updateList(ArrayList<String> keys){
        ArrayList<String> availableListingKeys = browseController.getAvailableListingKeys();
        for(String k: keys){
            if(!availableListingKeys.contains(k)) {
                keys.remove(k);
            }
        }
        populateGrid(keys);
    }

    /**
     * Fetches a list of available listings from the controller and adds them all to the FXML flowpane with populateGrid()
     */
    void updateList() {
        ArrayList<String> availableListingKeys = browseController.getAvailableListingKeys();
        populateGrid(availableListingKeys);
    }

    /**
     * Populates the grid och Listings to show the user
     * @param availableListingKeys Keys for listings to show
     */
    private void populateGrid (ArrayList<String> availableListingKeys){
        int index0 = 0;
        int index1 =0;
        listProducts.getChildren().clear();
        for(String key: availableListingKeys) {
            String[] listingData = browseController.getListingData(key);
            listProducts.add(new ListingItem(this.handler, listingData), index0,index1 );
            index0++;
            if( index0 == 2){
                index1++;
                index0 = 0;
            }
        }
    }

    /**
     * Get called when a user search for something in application
     */
    @FXML
    public void search() {
        System.out.println("Searching..");
        updateList(browseController.search(searchField.getText()));
        System.out.println("updated list");
    }

    /**
     * Method runs when scene is switched to so flowpane gets updated on scene switch.
     */
    @Override
    public void update() {
        updateList();
    }

    //CategoryButtons

    @FXML
    public void CatButton1Pressed() {
        updateList(browseController.getListingsByCategory(catButton1.getText()));

    }
    @FXML
    public void CatButton2Pressed() {
        updateList(browseController.getListingsByCategory(catButton2.getText()));

    }
    @FXML
    public void CatButton3Pressed() {
        updateList(browseController.getListingsByCategory(catButton3.getText()));

    }
    @FXML
    public void CatButton4Pressed() {
        updateList(browseController.getListingsByCategory(catButton4.getText()));

    }
    @FXML
    public void CatButton5Pressed() {
        updateList(browseController.getListingsByCategory(catButton5.getText()));

    }
    @FXML
    public void CatButton6Pressed() {
        updateList(browseController.getListingsByCategory(catButton6.getText()));

    }
    @FXML
    public void CatButton7Pressed() {
        updateList(browseController.getListingsByCategory(catButton7.getText()));

    }
    @FXML
    public void CatButton8Pressed() {
        updateList(browseController.getListingsByCategory(catButton8.getText()));

    }
}
