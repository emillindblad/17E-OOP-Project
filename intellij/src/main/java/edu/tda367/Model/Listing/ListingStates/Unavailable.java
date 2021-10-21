package edu.tda367.Model.Listing.ListingStates;

public class Unavailable implements ListingState {
    @Override
    public String getStatusText() {
        return "Betalad och uthyrd";
    }

    @Override
    public String getButtonText() {
        return "";
    }

    @Override
    public boolean getAdvanceBookingState() {
        return false;
    }

    @Override
    public ListingState advanceListingState() {
        System.out.println("Listing: Unavailable");
        return new Returned();
    }

    @Override
    public boolean getIsAvailable() {
        return false;
    }

    @Override
    public String toString() {
        return "UNAVAILABLE";
    }
}
