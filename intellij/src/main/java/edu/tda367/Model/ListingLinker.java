package edu.tda367.Model;

import edu.tda367.Model.UserPackage.UserHandler;
import java.util.ArrayList;

/**
 * A bridge between ListingHandler and UserHandler used for adding and deleting Listing IDs to a user
 * @author Emil Lindblad
 */
public class ListingLinker {
    private UserHandler userHander;

    /**
     * Constructor for ListingLinker. Gets an instance of UserHandler
     */
    public ListingLinker() {
        this.userHander = UserHandler.getInstance();
    }

    /**
     * Gets a ListingID/Key and adds it to the creators list of listingids
     * @param key The listingid/key of a newly created listing
     */
    public void linkListing(String key) {
        userHander.addListingId(key);
    }

    /**
     * Removes a listingid/key from a users list of listingids
     * @param key The key of the removed listing
     */
    public void removeLink(String key) {
        userHander.removeListingId(key);
    }
}
