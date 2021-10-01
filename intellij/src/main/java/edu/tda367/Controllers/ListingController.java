package edu.tda367.Controllers;

import edu.tda367.InputChecker;
import edu.tda367.Listing.Category;
import edu.tda367.Listing.Listing;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.scenes.Home;
import javafx.fxml.FXML;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListingController implements Controller {
    private final ListingHandler listingHandler;

    public ListingController() {
        listingHandler = new ListingHandler();
        //listingHandler.createListing("P.R.I.T. Grill", new Category("test"), "testing stuff", 4, 160, LocalDateTime.of(2021,9,10,9,0), LocalDateTime.of(2021,9,10,9,1));
    }


    public String createListing(String[] formData) {
        boolean foo = validateData(formData);
        System.out.println(foo);
         if (foo) { //Return true if valid input.
             listingHandler.createListingFromString(formData);
             //switch scene
             return "Success";
         }
         else {
             return "Error";
         }
    }

    public ArrayList<String> getCategoryNames() {
        return listingHandler.getCategoryNames();
    }

    public boolean validateData(String[] formData) {
        return (InputChecker.checkForNumber(formData[2])); // Should return true if valid input
    }

    public int getPrice() {
        return listingHandler.getListings().get(0).getPrice();
    }
}
