package edu.tda367.Booking;

import edu.tda367.Listing.Listing;

public class Booking {

    private BookingState bookingState = BookingState.PENDING;
    private final User customer;
    private final Listing listing;

    public Booking(User customer, Listing listing) {
        this.customer = customer;
        this.listing = listing;
    }

    public BookingState getBookingState() {
        return bookingState;
    }

    private void advanceBookingState() {
        if (bookingState == BookingState.ACCEPTED) {
            bookingState = BookingState.PAYED;
        }

        if (bookingState == BookingState.PENDING) {
            bookingState = BookingState.ACCEPTED;
        }
    }
    /*
    private boolean getBookingAccepted() {
        if (listing.bookingIsAccepted(this)) {
            advanceBookingState();
            return true;
        }

        return false;
    }*/

}
