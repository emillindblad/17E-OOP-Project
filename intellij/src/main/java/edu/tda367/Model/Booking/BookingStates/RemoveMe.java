package edu.tda367.Model.Booking.BookingStates;

public class RemoveMe implements BookingState {
    @Override
    public String getStatusText() {
        return "Borttagen!";
    }

    @Override
    public String getButtonText() {
        return "";
    }

    @Override
    public BookingState advanceBookingState() {
        return this;
    }

    @Override
    public boolean getIsToBeRemoved() {
        return true;
    }

    @Override
    public boolean getAdvanceListingState() {
        return false;
    }
}
