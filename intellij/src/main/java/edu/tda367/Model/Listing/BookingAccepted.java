package edu.tda367.Model.Listing;

class BookingAccepted implements ListingState {
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
        return true;
    }

    @Override
    public ListingState advanceListingState() {
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
