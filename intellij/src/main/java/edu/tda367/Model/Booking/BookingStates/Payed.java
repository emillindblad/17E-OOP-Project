package edu.tda367.Model.Booking.BookingStates;

public class Payed implements BookingState {
    @Override
    public String getStatusText() {
        return "Bokning betalad";
    }

    @Override
    public String getButtonText() {
        return "Återlämna";
    }

    @Override
    public BookingState advanceBookingState() {
        return new Returned();
    }

    @Override
    public boolean getRemoveAble() {
        return false;
    }
}
