package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;

import java.util.ArrayList;

public class BrowseController implements Controller {
    ListingHandler listingHandler;
    UserHandler uHandler;

    public BrowseController() {
        this.listingHandler = ListingHandler.getInstance();
        this.uHandler = UserHandler.getInstance();
    }

    public ArrayList<Listing>  getAvailableListings() {
        return listingHandler.getAvailableListings();
    }

    public void logOut() {
        uHandler.logOut();
    }

}
