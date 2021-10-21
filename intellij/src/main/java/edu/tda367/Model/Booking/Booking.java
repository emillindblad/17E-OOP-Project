package edu.tda367.Model.Booking;

import edu.tda367.Model.Booking.BookingStates.Pending;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.ListingStateListener;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.Model.Booking.BookingStates.BookingState;

/**
 * Class containing state and logic related to one booking
 */
public class Booking implements RentingItemEntry, ListingStateListener {

    private BookingState bookingState = new Pending();
    private final int userID;
    private final Listing listing;

    Booking(int userID, Listing listing) {
        this.userID = userID;
        this.listing = listing;
        listing.setListener(this);
        listing.advanceState();
    }

    /**
     * Getter for bookingState
     * @return bookingState
     */
    public boolean getIsToBeRemoved() {
        return bookingState.getIsToBeRemoved();
    }

    /**
     * Advances state of booking. Will update corresponding listing if necessary
     */
    @Override
    public void advanceState() {
        if (bookingState.getAdvanceListingState()) {
            listing.advanceState();
        }
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
        return bookingState.getStatusText();
    }

    /**
     * Getter for button text of RentingItemEntry
     * Will check if bookingState needs to updated for text to be correct
     * @return text depending on bookingState
     */
    @Override
    public String getButtonText() {
        return bookingState.getButtonText();
    }

    @Override
    public String getImageName() {
        return listing.getImageName();
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

    /**
     * Listener method. Advances state of booking
     */
    @Override
    public void listingStateChangedAction() {
        advanceState();
    }
}
