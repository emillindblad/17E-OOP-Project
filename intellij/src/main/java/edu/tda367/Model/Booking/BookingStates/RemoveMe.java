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
        System.out.println("Booking: RemoveMe");
        return this;
    }

    @Override
    public boolean getRemoveAble() {
        return true;
    }

    @Override
    public boolean getAdvanceListingState() {
        return false;
    }
}
