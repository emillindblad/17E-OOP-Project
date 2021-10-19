package edu.tda367.Model.Booking;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingState;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.Model.UserPackage.User;
import javafx.scene.image.Image;

import java.util.List;

public class Booking implements RentingItemEntry {

    private BookingState bookingState = BookingState.PENDING;
    private final User customer;
    private final Listing listing;

    Booking(User customer, Listing listing) {
        this.customer = customer;
        this.listing = listing;
        listing.advanceState();
    }

    BookingState getBookingState() {
        return bookingState;
    }

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

    // TODO images
    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public String getStatusText() {
        updateStateFromListing();
        return switch (bookingState) {
            case PENDING -> "Förfrågan skickad";
            case ACCEPTED -> "Förfrågan godkänd";
            case PAYED -> "Bokning betalad";
            case RETURNED -> "Vara tillbakalämnad";
            default -> "Tillbakalämnande godkänt";
        };
    }

    @Override
    public String getButtonText() {
        updateStateFromListing();
        return switch (bookingState) {
            case ACCEPTED -> "Betala";
            case PAYED -> "Återlämna";
            default -> "";
        };
    }

    private void updateStateFromListing() {
        ListingState lState = listing.getListingState();

        if (lState == ListingState.BOOKING_ACCEPTED && bookingState != BookingState.ACCEPTED) {
            bookingState = BookingState.ACCEPTED;
        }

        if (lState == ListingState.AVALIBLE && bookingState != BookingState.DONE) {
            bookingState = BookingState.DONE;
        }
    }

    /**
     * Getter for the User who is renting
     * @return The User who is renting
     */
    User getUser() {
        return customer;
    }

    /**
     * Getter for the rented Listing
     * @return The rented Listing
     */
    @Override
    public Listing getListing() {
        return listing;
    }
}
