package edu.tda367.View.scenes;

import edu.tda367.Controllers.BrowseController;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.View.SceneHandler;

import java.awt.*;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class BrowseListings extends AbstractHyroScene {
    BrowseController browseController;

    @FXML
    FlowPane listProducts;


    public BrowseListings(SceneHandler handler) throws IOException {
        super("browse", handler);
        browseController = new BrowseController();
        System.out.println("nice");
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


    void updateList() {
        System.out.println("Loaded products");
        ArrayList<Listing> listings = browseController.getAvailableListings();
        System.out.println("Current listings:"+listings.size());
        listProducts.getChildren().clear();
        for(int i = 0; i < listings.size(); i++)
        {
            //System.out.println((listings.get(i).getPrice()));
            listProducts.getChildren().add(new ListingItem(listings.get(i).getPrice(), listings.get(i).getProduct().getProdName(), listings.get(i).getProduct().getCategoryName()));
            //System.out.println(listProducts.getChildren().get(0));
        }
    }

    @Override
    public void update() {
        updateList();
    }
}
