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

    /**
     * Opens detailview of a listing
     * @param listingId id of listing to view
     * @throws IOException Listing not found
     */
    public void switchToListing(String listingId) throws IOException {
        Listing listing = listingHandler.getListingFromKey(listingId);
        sceneHandler.switchToListingView(listing);
    }

    /**
     * Constructor
     * @param handler SceneHandler
     */
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

    /**
     * method to chain a search from the view to handler
     * @param searchWord word that was searched for
     * @return List of keys to matching Listings
     */
    public ArrayList<String> search (String searchWord) {
        return listingHandler.getSortedKeys(searchWord);  //returnerar en lista med sorterade keys
    }

    /**
     * Sorts Listings by category and returns the desired one
     * @param categoryName Name of category
     * @return List of keys for Listings in chosen category
     */
    public ArrayList<String> getListingsByCategory (String categoryName) {
        return listingHandler.getAvailableListingKeys(categoryName);
    }
}
