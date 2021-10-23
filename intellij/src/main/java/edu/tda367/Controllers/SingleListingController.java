package edu.tda367.Controllers;

import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;

/**
 * Controller for the view of a single listing
 */
public class SingleListingController extends AbstractController {
    private ListingHandler listingHandler;
    private UserHandler userHandler;
    private BookingHandler bookingHandler;

    public SingleListingController(SceneHandler handler) {
        super(handler);
        this.listingHandler = ListingHandler.getInstance();
        this.userHandler = UserHandler.getInstance();
        this.bookingHandler = BookingHandler.getInstance();
    }

    /**
     * Activated by rent button it creates a Booking with the current User and Listing
     * @param listingID The listingID of the Listing to be rented
     */
    public void rentListing(String listingID) {
        bookingHandler.createBooking(userHandler.getUserID(), listingHandler.getListingFromKey(listingID));
    }

    /**
     * Used to check if the User is viewing their own Listing
     * @param listingUserID
     * @return True if the Listing belongs to the logged in User
     */
    public boolean isMyListing(int listingUserID) {
        return listingUserID == userHandler.getUserID();
    }
}
