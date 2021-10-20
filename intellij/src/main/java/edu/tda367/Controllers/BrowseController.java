package edu.tda367.Controllers;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;

import java.io.IOException;
import java.util.ArrayList;

public class BrowseController implements Controller {
    private ListingHandler listingHandler;
    private SceneHandler sceneHandler;
    UserHandler uHandler;

    public void switchToListing(String listingId) throws IOException {
        Listing listing = listingHandler.getListingFromKey(listingId);
        sceneHandler.switchToListingView(listing);
    }


    public BrowseController(SceneHandler handler) {
        this.listingHandler = ListingHandler.getInstance();
        this.sceneHandler = handler;
        this.uHandler = UserHandler.getInstance();
    }

    public ArrayList<String> getAvailableListingKeys() {
        return listingHandler.getAvailableListingKeys();
    }

    public Listing getListingFromKey(String key) {
        return listingHandler.getListingFromKey(key);
    }

    public String[] getListingData(String key) {
        return listingHandler.getListingData(key);
    }

    public void logOut() {
        uHandler.logOut();
    }

    public ArrayList<String> search (String searchWord) {
        return listingHandler.getSortedKeys(searchWord);  //returnerar en lista med sorterade keys

    }
}
