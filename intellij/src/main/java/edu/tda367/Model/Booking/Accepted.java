package edu.tda367.Model.Booking;

class Accepted extends BookingState {
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
        return new Payed();
    }

    @Override
    public boolean getIsToBeRemoved() {
        return false;
    }

    @Override
    public boolean getAdvanceListingState() {
        return true;
    }
}