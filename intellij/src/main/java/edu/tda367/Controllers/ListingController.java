package edu.tda367.Controllers;

import edu.tda367.Listing.Category;
import edu.tda367.Listing.Listing;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.View.scenes.Home;

import java.time.LocalDateTime;

public class ListingController implements Controller {
    private final ListingHandler listingHandler;

    public ListingController() {
        listingHandler = new ListingHandler();
        listingHandler.createListing("P.R.I.T. Grill", new Category("test"), "testing stuff", 4, 160, LocalDateTime.of(2021,9,10,9,0), LocalDateTime.of(2021,9,10,9,1));
    }


    public int getPrice() {
        return listingHandler.getListings().get(0).getPrice();
    }
}
