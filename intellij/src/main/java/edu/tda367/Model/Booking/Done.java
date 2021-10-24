package edu.tda367.Model.Booking;

class Done implements BookingState {
    @Override
    public String getStatusText() {
        return "Tillbakalämnande godkänt";
    }

    @Override
    public String getButtonText() {
        return "Ta bort";
    }

    @Override
    public BookingState advanceBookingState() {
        return new RemoveMe();
    }

    @Override
    public boolean getIsToBeRemoved() {
        return false;
    }

    @Override
    public boolean getAdvanceListingState() {
        return false;
    }

    @Override
    public String toString() { return "DONE"; }
}
