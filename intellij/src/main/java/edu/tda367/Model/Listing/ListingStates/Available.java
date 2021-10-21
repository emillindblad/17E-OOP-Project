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
        System.out.println("Listing: Available");
        return new BookingSent();
    }

    @Override
    public boolean getIsAvailable() {
        return true;
    }

    @Override
    public String toString() {
        return "AVAILABLE";
    }


}
