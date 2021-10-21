package edu.tda367.Model.Booking;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingState;
import edu.tda367.Model.RentingItemEntry;

/**
 * A class representing the contract between a User and a Listing they are renting
 * @author Eimer Ahlstedt
 * @author Erik Larsson
 */
public class Booking implements RentingItemEntry {

    private BookingState bookingState = BookingState.PENDING;
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
    public BookingState getBookingState() {
        return bookingState;
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
                listing.advanceState();
                break;

            case PAYED:
                bookingState = BookingState.RETURNED;
                listing.advanceState();
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
     * Will check if bookingState needs to updated for text to be correct
     * @return text depending on bookingState
     */
    @Override
    public String getButtonText() {
        updateStateFromListing();
        return switch (bookingState) {
            case ACCEPTED -> "Betala";
            case PAYED -> "Återlämna";
            case DONE -> "Ta bort";
            default -> "";
        };
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
