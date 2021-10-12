package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;

import java.util.ArrayList;

public class BrowseController implements Controller {
    ListingHandler listingHandler;
    

    public BrowseController() {
        this.listingHandler = ListingHandler.getInstance();
    }

    public ArrayList<Listing>  getAvailableListings() {
        return listingHandler.getAvailableListings();
    }

    public void logOut() {
        uHandler.logOut();
    }

}
