package edu.tda367.Model.Booking;

class Returned implements BookingState {
    @Override
    public String getStatusText() {
        return "Vara tillbakalämnad";
    }

    @Override
    public String getButtonText() {
        return "";
    }

    @Override
    public BookingState advanceBookingState() {
        return new Done();
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
    public String toString() { return "RETURNED"; }
}
