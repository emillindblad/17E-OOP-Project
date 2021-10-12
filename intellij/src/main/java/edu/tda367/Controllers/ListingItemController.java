package edu.tda367.Controllers;

import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;

public class ListingItemController {
    private SceneHandler handler;

    private ListingHandler listingHandler
    public ListingItemController(SceneHandler handler) {
        this.handler = handler;
    }

    public switchToListing(String productName) {
        handler.switchTo("Listing", listingHandler.getListingByProductName(productName));
    }
}
