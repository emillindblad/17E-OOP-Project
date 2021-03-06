package edu.tda367.Controllers;

import edu.tda367.Model.Booking.Booking;
import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.scenes.RentingItem;
import javafx.scene.layout.FlowPane;
import java.util.ArrayList;

/**
 * MVC Controller for RentingPage
 */
public class MyListingsController extends AbstractController {

    private final UserHandler uHandler = UserHandler.getInstance();
    private final ListingHandler lHandler = ListingHandler.getInstance();
    private final BookingHandler bHandler = BookingHandler.getInstance();

    /**
     * Constructor
     * @param handler SceneHandler used to switch scenes
     */
    public MyListingsController(SceneHandler handler) {
        super(handler);
    }

    /**
     * Adds updated RentingItems to FlowPanes
     * @param listingsPane FlowPane where listings are to be added
     * @param bookingsPane FlowPane where bookings are to be added
     */
    public void update(FlowPane listingsPane, FlowPane bookingsPane) {
        updateListings(listingsPane);
        updateBookings(bookingsPane);
    }

    private void updateListings(FlowPane listingsPane) {
        ArrayList<String> myListingIds = uHandler.getLoggedInUser().getMyListingIds();
        System.out.println(myListingIds);
        ArrayList<Listing> listings = new ArrayList<>();
        for (String id : myListingIds) {
            listings.add(lHandler.getListingFromKey(id));
        }
        System.out.println(listings);
        for (Listing l : listings) {
            listingsPane.getChildren().add(new RentingItem(new RentingItemController(handler, bHandler), l));
        }
    }

    private void updateBookings(FlowPane bookingsPane) {
        ArrayList<Booking> bookings = bHandler.getMyBookings();
        for (Booking b : bookings) {
            bookingsPane.getChildren().add(new RentingItem(new RentingItemController(handler, bHandler), b));
        }
    }
}
