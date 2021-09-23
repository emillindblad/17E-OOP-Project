package edu.tda367.Controllers;

import edu.tda367.Listing.Listing;
import edu.tda367.Listing.ListingHandler;

import java.util.ArrayList;
import java.util.List;

public class BrowseController implements Controller {
    ListingHandler listingHandler;

    public BrowseController() {
        this.listingHandler = new ListingHandler();
    }

    public ArrayList<Listing>  getAvailableListings() {
        return listingHandler.getAvailableListings();
    }

}
