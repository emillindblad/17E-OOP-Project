package edu.tda367.Controllers;

import edu.tda367.Model.RentingItemEntry;

/**
 * MVC Controller for RentingItems
 */
public class RentingItemController {

    /**
     * Advances state of RentingItem according to Model algorithm
     * @param entry the Booking/Listing whose state is to be advanced
     */
    public void buttonAction(RentingItemEntry entry) {
        entry.advanceState();
    }
}
