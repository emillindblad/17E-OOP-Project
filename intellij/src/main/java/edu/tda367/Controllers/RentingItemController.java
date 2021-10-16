package edu.tda367.Controllers;

import edu.tda367.Model.RentingItemEntry;

public class RentingItemController {

    public void buttonAction(RentingItemEntry entry) {
        entry.advanceState();
    }
}
