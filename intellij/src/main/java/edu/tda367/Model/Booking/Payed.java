package edu.tda367.Model.Booking;

class Payed implements BookingState {
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
    public boolean getIsToBeRemoved() {
        return false;
    }

    @Override
    public boolean getAdvanceListingState() {
        return true;
    }
}
