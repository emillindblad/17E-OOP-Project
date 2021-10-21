package edu.tda367.Model.Listing;

class Unavailable extends ListingState {
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
