package edu.tda367.Controllers;


import edu.tda367.Model.Booking.Booking;
import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import edu.tda367.View.scenes.RentingItem;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class MyListingsController {

    private final SceneHandler sHandler;
    private final UserHandler uHandler = UserHandler.getInstance();
    private final ListingHandler lHandler = ListingHandler.getInstance();
    private final BookingHandler bHandler = BookingHandler.getInstance();

    public MyListingsController(SceneHandler handler) {
        this.sHandler = handler;
    }

    public void goBack() {
        sHandler.switchTo("browse");
    }

    public void update(FlowPane listingsPane, FlowPane bookingsPane) {
        updateListings(listingsPane);
        updateBookings(bookingsPane);
    }

    private void updateListings(FlowPane listingsPane) {
        ArrayList<Listing> listings = lHandler.getMyListings(uHandler.getUserID());
        for (Listing l : listings) {
            listingsPane.getChildren().add(new RentingItem(new RentingItemController(), l));
        }
    }

    private void updateBookings(FlowPane bookingsPane) {
        ArrayList<Booking> bookings = bHandler.getMyBookings();
        for (Booking b : bookings) {
            bookingsPane.getChildren().add(new RentingItem(new RentingItemController(), b));
        }

    }
}
