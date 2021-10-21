package edu.tda367.Model.Listing.ListingStates;

public class Returned implements ListingState {
    @Override
    public String getStatusText() {
        return "Återlämnad";
    }

    @Override
    public String getButtonText() {
        return "Ja, jag har fått den";
    }

    @Override
    public boolean getAdvanceBookingState() {
        return true;
    }

    @Override
    public ListingState advanceListingState() {
        return new Available();
    }

    @Override
    public boolean getIsAvailable() {
        return false;
    }
}
