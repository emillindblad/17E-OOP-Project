package edu.tda367.Model.Booking.BookingStates;

public class Returned implements BookingState {
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
        System.out.println("Booking: Returned");
        return new Done();
    }

    @Override
    public boolean getRemoveAble() {
        return false;
    }

    @Override
    public boolean getAdvanceListingState() {
        return false;
    }
}
