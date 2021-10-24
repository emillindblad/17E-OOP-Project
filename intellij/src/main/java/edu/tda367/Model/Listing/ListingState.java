package edu.tda367.Model.Listing;

/**
 * "INTERFACE" for states of Listing
 * #######################################################################################################
 * #                                THIS SHOULD BE AN INTERFACE                                          #
 * #        IT WAS MADE A CONCRETE CLASS DUE TO GSON BEING THE WAY IT IS AND TIME CONSTRAINTS            #
 * #######################################################################################################
 */
interface ListingState {
    /**
     * Getter for status text
     *  @return status text
     */
    String getStatusText();
    /**
     * Getter for button text
     * @return button text
     */
    String getButtonText();

    /**
     * Returns whether booking should advance state when listing does
     * if true, booking is notified through observer pattern
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

