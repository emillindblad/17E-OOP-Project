package edu.tda367.Model.Booking;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.RentingItemEntry;

/**
 * A class representing the contract between a User and a Listing they are renting
 * @author Eimer Ahlstedt
 * @author Erik Larsson
 */
public class Booking implements RentingItemEntry {

    private BookingState bookingState = BookingState.PENDING;
    private final int userID;
    private final String listingKey;

    Booking(int userID, String listingID) {
        this.userID = userID;
        this.listingKey = listingID;
        getListing().advanceState();
    }

    /**
     * Getter for bookingState
     * @return bookingState
     */
    public boolean getIsToBeRemoved() {
        return bookingState == BookingState.REMOVEME;
    }

    /**
     * Advances state of booking. Will update corresponding listing if necessary
     */
    @Override
    public void advanceState() {
        BookingState currentState = bookingState;

        switch (currentState) {

            case PENDING:
                bookingState = BookingState.ACCEPTED;
                break;

            case ACCEPTED:
                bookingState = BookingState.PAYED;
                getListing().advanceState();
                break;

            case PAYED:
                bookingState = BookingState.RETURNED;
                getListing().advanceState();
                break;

            case RETURNED:
                bookingState = BookingState.DONE;
                break;

            case DONE:
                bookingState = BookingState.REMOVEME;
                break;

            default:
                break;
        }
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
        return switch (bookingState) {
            case PENDING -> "Förfrågan skickad";
            case ACCEPTED -> "Förfrågan godkänd";
            case PAYED -> "Bokning betalad";
            case RETURNED -> "Vara tillbakalämnad";
            case DONE -> "Tillbakalämnande godkänt";
            default -> "Borttagen!";
        };
    }

    /**
     * Getter for button text of RentingItemEntry
     * @return text depending on bookingState
     */
    @Override
    public String getButtonText() {
        return switch (bookingState) {
            case ACCEPTED -> "Betala";
            case PAYED -> "Återlämna";
            case DONE -> "Ta bort";
            default -> "";
        };
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
        return ListingHandler.getInstance().getListingFromKey(listingKey);
    }

    /**
     * Getter for userID of user making booking
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

}
