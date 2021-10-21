package edu.tda367.Model.Booking;

import edu.tda367.Model.Booking.BookingStates.Pending;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingState;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.Model.Booking.BookingStates.BookingState;

public class Booking implements RentingItemEntry {

    private BookingState bookingState = new Pending();
    private final int userID;
    private final Listing listing;

    Booking(int userID, Listing listing) {
        this.userID = userID;
        this.listing = listing;
        listing.advanceState();
    }

    /**
     * Getter for bookingState
     * @return bookingState
     */
    public boolean getIsToBeRemoved() {
        return bookingState.getRemoveAble();
    }

    /**
     * Advances state of booking. Will update corresponding listing if necessary
     */
    @Override
    public void advanceState() {
        bookingState = bookingState.advanceBookingState();
    }

    @Override
    public boolean getClickable() {
        return false;
    }

    @Override
    public String getProductName() {
        return listing.getProduct().getProdName();
    }

    @Override
    public int getPrice() {
        return listing.getPrice();
    }

    @Override
    public String getCategoryName() {
        return listing.getListingCategory().getCategoryName();
    }

    /**
     * Getter for status text of RentingItemEntry
     * Will check if bookingState needs to updated for text to be correct
     * @return text depending on bookingState
     */
    @Override
    public String getStatusText() {
        updateStateFromListing();
        return bookingState.getStatusText();
    }

    /**
     * Getter for button text of RentingItemEntry
     * Will check if bookingState needs to updated for text to be correct
     * @return text depending on bookingState
     */
    @Override
    public String getButtonText() {
        updateStateFromListing();
        return bookingState.getButtonText();
    }

    @Override
    public String getImageName() {
        return listing.getImageName();
    }

    private void updateStateFromListing() {
        ListingState lState = listing.getListingState();

        if (lState == ListingState.BOOKING_ACCEPTED && bookingState != BookingState.ACCEPTED) {
            bookingState = BookingState.ACCEPTED;
        }

        if (lState == ListingState.AVAILABLE && bookingState != BookingState.DONE && bookingState != BookingState.REMOVEME) {
            bookingState = BookingState.DONE;
        }
    }

    /**
     * Getter for the rented Listing
     * @return The rented Listing
     */
    @Override
    public Listing getListing() {
        return listing;
    }

    /**
     * Getter for userID of user making booking
     * @return userID
     */
    public int getUserID() {
        return userID;
    }
}
