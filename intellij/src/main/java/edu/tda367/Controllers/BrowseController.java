package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.Model.UserPackage.UserHandler;

import java.io.IOException;
import java.util.ArrayList;

public class BrowseController implements Controller {
    private ListingHandler listingHandler;
    private SceneHandler sceneHandler;
    UserHandler uHandler;

    public void switchToListing(String listingId) throws IOException {
        Listing listing = listingHandler.getListingByListingId(listingId);
        System.out.println(listing);
        sceneHandler.switchToListingView(listing);
    }


    public BrowseController(SceneHandler handler) {
        this.listingHandler = ListingHandler.getInstance();
        this.sceneHandler = handler;
        this.uHandler = UserHandler.getInstance();
    }

    public ArrayList<Listing>  getAvailableListings() {
        return listingHandler.getAvailableListings();
    }

    public void logOut() {
        uHandler.logOut();
    }

    public void search (String searchWord) {
        listingHandler.sortListings(searchWord);
    }
}
