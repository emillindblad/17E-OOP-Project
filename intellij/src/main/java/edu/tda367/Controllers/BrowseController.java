package edu.tda367.Controllers;

import edu.tda367.Listing.Listing;
import edu.tda367.Listing.ListingHandler;

import java.util.ArrayList;

class BrowseController implements Controller {
    ListingHandler listingHandler;

    protected ArrayList<Listing>  getListings() {
        return listingHandler.getListings();
    }
}
