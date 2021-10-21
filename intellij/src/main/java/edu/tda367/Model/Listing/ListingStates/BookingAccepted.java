package edu.tda367.Model.Listing.ListingStates;

public class BookingAccepted implements ListingState {
    @Override
    public String getStatusText() {
        return "Inväntar betalning";
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
        System.out.println("Listing: Accepted");
        return new Unavailable();
    }

    @Override
    public boolean getIsAvailable() {
        return false;
    }

    @Override
    public String toString() {
        return "BOOKING_ACCEPTED";
    }
}
