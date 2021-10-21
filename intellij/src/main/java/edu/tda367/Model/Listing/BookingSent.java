package edu.tda367.Model.Listing;

class BookingSent extends ListingState {
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
        return false;
    }

    @Override
    public ListingState advanceListingState() {
        return new BookingAccepted();
    }

    @Override
    public boolean getIsAvailable() {
        return false;
    }

    @Override
    public String toString() {
        return "BOOKING_SENT";
    }
}
