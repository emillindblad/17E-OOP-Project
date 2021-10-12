package edu.tda367.Model.Booking;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.UserPackage.User;

import java.util.ArrayList;
import java.util.List;

public class BookingHandler {
    private static BookingHandler instance;
    private final ArrayList<Booking> bookings;

    private BookingHandler() {
        bookings = getSavedBookings();
    }

    public static BookingHandler getInstance() {
        if (instance == null) {
            instance = new BookingHandler();
        }
        return instance;
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

    /**
     * Gets saved Bookings from database
     * @author Erik Larsson
     * @return An ArrayList containing Booking objects
     *
     */
    private ArrayList<Booking> getSavedBookings() {
        ArrayList<Booking> listingstmp = new ArrayList<>();
        JSONReader reader = new JSONReader();
        List<Booking> savedUsers = reader.read(Booking[].class, "bookings");
        savedUsers.forEach(l ->
        {
            if (l == null) {
                System.out.println("null object in json file");
            } else {
                listingstmp.add(l);
            }
        });
        return listingstmp;
    }

    /**
     * Writes Bookings to database
     * @author Erik Larsson
     */
    public void writeBookings() {
        JSONWriter writer = new JSONWriter();
        writer.write(bookings, "bookings");
    }
}