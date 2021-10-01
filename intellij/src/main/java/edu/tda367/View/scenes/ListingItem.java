package edu.tda367.View.scenes;


import edu.tda367.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class ListingItem extends AnchorPane {

    @FXML
    Label itemPrice;

    @FXML
    Label itemProductName;

    @FXML
    Label itemCategory;

    protected ListingItem(int price, String productName, String itemCategory) {
        FXMLLoader loader = App.loadFXML("listingitem");
        System.out.println(loader.toString());
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize(price, productName, itemCategory);
    }

    void initialize(int price, String productName, String itemCategory) {
        this.itemProductName.setText(productName);
        this.itemPrice.setText(String.valueOf(price));
        this.itemCategory.setText(itemCategory);
    }

}
