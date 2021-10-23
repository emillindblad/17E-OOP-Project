package edu.tda367.Model.Booking;


/**
 * "INTERFACE" for states of booking
 * #######################################################################################################
 * #                                THIS SHOULD BE AN INTERFACE                                          #
 * #        IT WAS MADE A CONCRETE CLASS DUE TO GSON BEING THE WAY IT IS AND TIME CONSTRAINTS            #
 * #######################################################################################################
 */
class BookingState {
    /**
     * Getter for status text
     * @return status text
     */
    public String getStatusText() {
        return "ERROR";
    }

    /**
     * Returns a new instance of the next booking state
     * @return next booking state
     */
    public String getButtonText() {
        return "ERROR";
    }

    /**
     * Returns a new instance of the next booking state
     * @return next booking state
     */
    public BookingState advanceBookingState() {
        return null;
    }

    /**
     * Returns true if booking is to be removed, depending on state
     * @return true if to be removed, else false
     */
    public boolean getIsToBeRemoved() {
        return false;
    }

    /**
     * Returns whether listing should advance state when booking does
     * @return boolean if listing should advance
     */
    public boolean getAdvanceListingState() {
        return false;
    }
}
