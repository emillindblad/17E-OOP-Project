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
    private final String listingID;

    Booking(int userID, String listingID) {
        this.userID = userID;
        this.listingID = listingID;
        getListing().advanceState();
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
            getListing().advanceState();
        }
        BookingState state = evalState().advanceBookingState();
        System.out.println("Advance BookingState: "+state.toString());
        bookingState = state.toString();
    }

    @Override
    public boolean getClickable() {
        return false;
    }

    @Override
    public String getProductName() {
        return getListing().getProduct().getProdName();
    }

    @Override
    public int getPrice() {
        return getListing().getPrice();
    }

    @Override
    public String getCategoryName() {
        return getListing().getListingCategory().getCategoryName();
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
        if (getListing().getUpdateBookingState()) {
            advanceState();
        }
    }

    @Override
    public String getImageName() {
        return getListing().getImageName();
    }

    /**
     * Getter for the rented Listing
     * @return The rented Listing
     */
    @Override
    public Listing getListing() {
        return ListingHandler.getInstance().getListingFromKey(listingID);
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
