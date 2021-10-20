package edu.tda367.Controllers;

import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Booking.DeleteBookingListener;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.View.SceneHandler;

import java.io.IOException;

/**
 * MVC Controller for RentingItems
 */
public class RentingItemController {

    private final SceneHandler sceneHandler;
    private final DeleteBookingListener listener;

    public RentingItemController(SceneHandler handler, DeleteBookingListener listener) {
        this.sceneHandler = handler;
        this.listener = listener;
    }

    /**
     * Advances state of RentingItem according to Model algorithm
     * @param entry the Booking/Listing whose state is to be advanced
     */
    public void buttonAction(RentingItemEntry entry) {
        entry.advanceState();
        listener.deleteCompletedBookings();
    }

    public void goToSettings(RentingItemEntry entry) throws IOException {
        sceneHandler.switchToListingSettings(entry);
    }
}
