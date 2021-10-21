package edu.tda367.Model.Booking.BookingStates;

public class Pending implements BookingState {
    @Override
    public String getStatusText() {
        return "Förfrågan skickad";
    }

    @Override
    public String getButtonText() {
        return "";
    }

    @Override
    public BookingState advanceBookingState() {
        return new Accepted();
    }

    @Override
    public boolean getRemoveAble() {
        return false;
    }
}
