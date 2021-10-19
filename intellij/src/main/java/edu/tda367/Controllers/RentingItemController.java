package edu.tda367.Controllers;

import edu.tda367.Model.RentingItemEntry;
import edu.tda367.View.SceneHandler;

/**
 * MVC Controller for RentingItems
 */
public class RentingItemController {

    private final SceneHandler sceneHandler;

    public RentingItemController(SceneHandler handler) {
        this.sceneHandler = handler;
    }

    /**
     * Advances state of RentingItem according to Model algorithm
     * @param entry the Booking/Listing whose state is to be advanced
     */
    public void buttonAction(RentingItemEntry entry) {
        entry.advanceState();
    }

    public void goToSettings(RentingItemEntry entry) {
        sceneHandler.switchTo("listingsettings");
    }
}
