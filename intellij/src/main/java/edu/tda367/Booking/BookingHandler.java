package edu.tda367.Booking;

import edu.tda367.Listing.Listing;
import edu.tda367.UserPackage.User;

import java.util.ArrayList;

public class BookingHandler {
    private final ArrayList<Booking> bookings;

    public BookingHandler() {
        this.bookings = new ArrayList();
    }

    public void createBooking(User customer, Listing listing) {
        //TODO: Check if trying to book own Listing
        Booking booking = new Booking(customer, listing);
        bookings.add(booking);
    }
}