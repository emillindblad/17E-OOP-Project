package edu.tda367.Model.Listing;

/**
 * Interface for listingState
 */
interface ListingState {
    /**
     * Getter for status text
     * @return status text
     */
    String getStatusText();
    /**
     * Getter for button text
     * @return button text
     */
    String getButtonText();

    /**
     * Returns whether booking should advance state when listing does
     * If true, booking is notified through observer pattern
     * @return boolean if booking is to advance state
     */
    boolean getAdvanceBookingState();

    /**
     * Returns a new instance of the next state
     * @return next state
     */
    ListingState advanceListingState();

    /**
     * Getter for whether listing is available, depending on state
     * @return boolean is available
     */
    boolean getIsAvailable();
}
