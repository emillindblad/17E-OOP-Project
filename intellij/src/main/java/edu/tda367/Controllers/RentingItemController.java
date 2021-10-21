package edu.tda367.Controllers;

import edu.tda367.Model.Booking.DeleteBookingListener;
import edu.tda367.Model.RentingItemEntry;

import java.io.IOException;

/**
 * MVC Controller for RentingItems
 */
public class RentingItemController extends AbstractController {

    private final DeleteBookingListener listener;

    public RentingItemController(SceneHandler handler, DeleteBookingListener listener) {
        super(handler);
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

    /**
     * Switches to setting view for clicked listing
     * @param entry RentingItemEntry containing listing to bw switched to
     * @throws IOException
     */
    public void goToSettings(RentingItemEntry entry) throws IOException {
        handler.switchToListingSettings(entry);
    }
}
