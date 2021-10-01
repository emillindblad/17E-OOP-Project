package edu.tda367.Booking;

import edu.tda367.Listing.Listing;
import edu.tda367.UserPackage.User;

import java.util.ArrayList;

public class BookingHandler {
    private final ArrayList<Booking> bookings;

    public BookingHandler() {
        this.bookings = new ArrayList();
    }

    public void createBooking(User customer, int userID, Listing listing) {
        if (userID == listing.getUserId()) {
            System.out.println("Can't book your own listing!");
        } else {
            Booking booking = new Booking(customer, listing);
            bookings.add(booking);
        }
    }

    public ArrayList<Booking> getBookings() { return bookings; }

    public BookingState getBookingState(int bookingIndex) {
        return bookings.get(bookingIndex).getBookingState();
    }

    public void advanceBookingState(int bookingIndex) { bookings.get(bookingIndex).advanceBookingState();}
}