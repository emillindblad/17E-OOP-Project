package edu.tda367.Controllers;

import edu.tda367.Listing.Listing;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;

import java.io.IOException;
import java.util.ArrayList;

public class BrowseController implements Controller {
    private ListingHandler listingHandler;
    private SceneHandler sceneHandler;

    public void switchToListing(String productName) throws IOException {
        Listing listing = listingHandler.getListingByProductName(productName);
        sceneHandler.switchToListingView(listing);
    }


    public BrowseController(SceneHandler handler) {
        this.listingHandler = new ListingHandler();
        this.sceneHandler = handler;
    }

    public ArrayList<Listing>  getAvailableListings() {
        return listingHandler.getAvailableListings();
    }

}
