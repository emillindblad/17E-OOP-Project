package edu.tda367.Model.Booking;

/**
 * "INTERFACE" for states of booking
 * #######################################################################################################
 * #                                THIS SHOULD BE AN INTERFACE                                          #
 * #        IT WAS MADE A CONCRETE CLASS DUE TO GSON BEING THE WAY IT IS AND TIME CONSTRAINTS            #
 * #######################################################################################################
 */
interface BookingState {
    /**
     * Getter for status text
     * @return status text
     */
    String getStatusText();

    /**
     * Returns a new instance of the next booking state
     * @return next booking state
     */
    String getButtonText();

    /**
     * Returns a new instance of the next booking state
     * @return next booking state
     */
    BookingState advanceBookingState();

    /**
     * Returns true if booking is to be removed, depending on state
     * @return true if to be removed, else false
     */
    boolean getIsToBeRemoved();

    /**
     * Returns whether listing should advance state when booking does
     * @return boolean if listing should advance
     */
    boolean getAdvanceListingState();
}

