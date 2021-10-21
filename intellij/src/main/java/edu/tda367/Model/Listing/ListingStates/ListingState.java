package edu.tda367.Model.Listing.ListingStates;

public interface ListingState {
    String toString();
    String getStatusText();
    String getButtonText();
    boolean getAdvanceBookingState();
    ListingState advanceListingState();
    boolean getIsAvailable();
}
