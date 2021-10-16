package edu.tda367.Model.Booking;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingState;
import edu.tda367.Model.UserPackage.User;

import java.util.ArrayList;
import java.util.List;

/**
 * BookingHandler is a handler for interacting with Bookings and their state
 * @author Erik Larsson
 */
public class BookingHandler {
    private static BookingHandler instance;
    private final ArrayList<Booking> bookings;

    /**
     * Private constructor, BookingHandler is a singleton
     */
    private BookingHandler() {
        bookings = getSavedBookings();
    }

    /**
     * Getter for instance of BookingHandler
     * @return The instance of BookingHandler
     */
    public static BookingHandler getInstance() {
        if (instance == null) {
            instance = new BookingHandler();
        }
        return instance;
    }

    /**
     * Creates a Booking with supplied parameters and adds it to Booking list
     * @param customer The User who wants to rent a product
     * @param userID The ID of the User who wants to rent a product
     * @param listing The Listing of the product that the User wants to rent
     */
    public void createBooking(User customer, int userID, Listing listing) {
        if (userID == listing.getUserId()) {
            System.out.println("Can't book your own listing!");
        } else if (listing.getListingState() != ListingState.AVALIBLE) {
            System.out.println("Listing not available");
        } else {
            Booking booking = new Booking(customer, listing);
            bookings.add(booking);
        }
    }

    /**
     * Getter for Booking list
     * @return An ArrayList containing Booking(s)
     */
    public ArrayList<Booking> getBookings() { return bookings; }

    /**
     * Getter for BookingState for a Booking specified by index in list
     * @param bookingIndex Index of Booking in bookings list
     * @return The BookingState of specified Booking
     */
    public BookingState getBookingState(int bookingIndex) {
        return bookings.get(bookingIndex).getBookingState();
    }

    /**
     * Advances the BookingState of a Booking specified by index in list
     * @param bookingIndex Index of Booking in bookings list
     */
    public void advanceBookingState(int bookingIndex) { bookings.get(bookingIndex).advanceState();}

    /**
     * Gets saved Bookings from database
     * @return An ArrayList containing Booking objects
     *
     */
    private ArrayList<Booking> getSavedBookings() {
        ArrayList<Booking> bookingstmp = new ArrayList<>();
        JSONReader reader = new JSONReader();
        List<Booking> savedBookings = reader.read(Booking[].class, "bookings");
        savedBookings.forEach(b ->
        {
            if (b == null) {
                System.out.println("null object in json file");
            } else {
                bookingstmp.add(b);
            }
        });
        return bookingstmp;
    }

    /**
     * Writes Bookings to database
     */
    public void writeBookings() {
        JSONWriter writer = new JSONWriter();
        writer.write(bookings, "bookings");
    }
}