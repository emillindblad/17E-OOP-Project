package edu.tda367;

public class Booking {

    private final String customerName;
    private BookingState bookingState = BookingState.PENDING;

    public Booking(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName; //Strings are immutable?
    }

    public BookingState getBookingState() {
        return bookingState;
    }
}
