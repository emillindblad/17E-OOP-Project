package edu.tda367.Model.Booking.BookingStates;

public interface BookingState {
    String getStatusText();
    String getButtonText();
    BookingState advanceBookingState();
    boolean getRemoveAble();
    boolean getAdvanceListingState();
}
