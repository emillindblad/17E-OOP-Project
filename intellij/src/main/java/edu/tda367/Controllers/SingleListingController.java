package edu.tda367.Controllers;

import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.Model.UserPackage.UserHandler;

public class SingleListingController implements Controller {
    private ListingHandler listingHandler;
    private SceneHandler sceneHandler;
    private UserHandler userHandler;
    private BookingHandler bookingHandler;

    public SingleListingController(SceneHandler handler) {
        this.listingHandler = ListingHandler.getInstance();
        this.sceneHandler = handler;
        this.userHandler = UserHandler.getInstance();
        this.bookingHandler = BookingHandler.getInstance();
    }

    public void rentListing(String listingID) {
        bookingHandler.createBooking(userHandler.getUserID(), listingHandler.getListingFromKey(listingID));
    }

    public boolean isMyListing(int listingUserID) {
        return listingUserID == userHandler.getUserID();
    }
}
