package edu.tda367.Model.Booking;

import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.Model.UserPackage.User;
import javafx.scene.image.Image;

class Booking implements RentingItemEntry {

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

    @Override
    public void advanceState() {

        BookingState currentState = bookingState;

        switch (currentState) {

            case PENDING:
                bookingState = BookingState.ACCEPTED;
                break;

            case ACCEPTED:
                bookingState = BookingState.PAYED;
                break;

            case PAYED:
                bookingState = BookingState.RETURNED;
                break;

            case RETURNED:
                bookingState = BookingState.DONE;
                break;

            default:
                break;
        }
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
        switch (bookingState) {
            case PENDING:
                bookingState = BookingState.ACCEPTED;
                return "Förfrågan skickad";

            case ACCEPTED:
                bookingState = BookingState.PAYED;
                return "Förfrågan godkänd";

            case PAYED:
                bookingState = BookingState.RETURNED;
                return "Bokning betalad";

            case RETURNED:
                bookingState = BookingState.DONE;
                return "Vara tillbakalämnad";

            default:
                return "Tillbakalämnande godkänt";
        }
    }

    @Override
    public String getButtonText() {
        switch (bookingState) {
            case ACCEPTED:
                bookingState = BookingState.PAYED;
                return "Betala";

            case PAYED:
                bookingState = BookingState.RETURNED;
                return "Återlämna";

            default:
                return "";
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
