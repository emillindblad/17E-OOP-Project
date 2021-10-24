package edu.tda367.Model.Booking;

import edu.tda367.Model.Listing.*;
import edu.tda367.Model.RentingItemEntry;

/**
 * A class representing the contract between a User and a Listing they are renting
 * @author Eimer Ahlstedt
 * @author Erik Larsson
 */
public class Booking implements RentingItemEntry {

    private String bookingState = "PENDING";
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
        return evalState().getIsToBeRemoved();
    }

    /**
     * Advances state of booking. Will update corresponding listing if necessary
     */
    @Override
    public void advanceState() {
        if (evalState().getAdvanceListingState()) {
            listing.advanceState();
        }
        BookingState state = evalState().advanceBookingState();
        System.out.println(state.toString());
        bookingState = state.toString();
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
        return evalState().getStatusText();
    }

    /**
     * Getter for button text of RentingItemEntry
     * @return text depending on bookingState
     */
    @Override
    public String getButtonText() {
        return evalState().getButtonText();
    }

    private void updateStateFromListing() {
        if (listing.getUpdateBookingState()) {
            advanceState();
        }
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

    private BookingState evalState() {
        BookingState state;
        switch (bookingState) {
            case "ACCEPTED":
                state = new Accepted();
                break;
            case "PAYED":
                state = new Payed();
                break;
            case "RETURNED":
                state = new Returned();
                break;
            case "DONE":
                state = new Done();
                break;
            case "REMOVEME":
                state = new RemoveMe();
                break;
            default:
                state = new Pending();
        }
        return state;
    }

}
