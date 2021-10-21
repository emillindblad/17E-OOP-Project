package edu.tda367.Model.Booking.BookingStates;

public class Accepted implements BookingState {
    @Override
    public String getStatusText() {
        return "Förfrågan godkänd";
    }

    @Override
    public String getButtonText() {
        return "Betala";
    }

    @Override
    public BookingState advanceBookingState() {
        System.out.println("Booking: Accepted");
        return new Payed();
    }

    @Override
    public boolean getRemoveAble() {
        return false;
    }

    @Override
    public boolean getAdvanceListingState() {
        return true;
    }
}
