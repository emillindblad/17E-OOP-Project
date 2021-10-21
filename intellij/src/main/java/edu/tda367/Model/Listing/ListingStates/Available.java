package edu.tda367.Model.Listing.ListingStates;

public class Available implements ListingState {
    @Override
    public String getStatusText() {
        return "Tillgänglig";
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
        return new BookingSent();
    }

    @Override
    public boolean getIsAvailable() {
        return true;
    }


}
