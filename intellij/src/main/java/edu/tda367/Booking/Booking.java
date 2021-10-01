package edu.tda367.Booking;

import edu.tda367.Listing.Listing;
import edu.tda367.UserPackage.User;

class Booking {

    private BookingState bookingState = BookingState.PENDING;
    private final User customer;
    private final Listing listing;

    Booking(User customer, Listing listing) {
        this.customer = customer;
        this.listing = listing;
    }

    BookingState getBookingState() {
        return bookingState;
    }

    void advanceBookingState() {

        BookingState currentState = bookingState;

        switch (currentState) {

            case PENDING:
                bookingState = BookingState.ACCEPTED;
                break;

            case ACCEPTED:
                bookingState = BookingState.PAYED;
                break;

            case PAYED:
                System.out.println("This booking is already payed!");
                break;
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
