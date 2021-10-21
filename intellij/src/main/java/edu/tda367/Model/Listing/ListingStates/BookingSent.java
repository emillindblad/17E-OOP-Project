package edu.tda367.Model.Listing.ListingStates;

public class BookingSent implements ListingState {
    @Override
    public String getStatusText() {
        return "Förfrågan mottagen";
    }

    @Override
    public String getButtonText() {
        return "Acceptera";
    }

    @Override
    public boolean getAdvanceBookingState() {
        return true;
    }

    @Override
    public ListingState advanceListingState() {
        return new BookingAccepted();
    }

    @Override
    public boolean getIsAvailable() {
        return false;
    }
}
