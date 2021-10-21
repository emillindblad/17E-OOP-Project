package edu.tda367.Model.Booking;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.UserPackage.UserHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * BookingHandler is a handler for interacting with Bookings and their state
 * @author Erik Larsson
 */
public class BookingHandler implements DeleteBookingListener {
    private static BookingHandler instance;
    private final ArrayList<Booking> bookings;
    private static UserHandler uHandler;

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
     * @param userID The ID of the User who wants to rent a product
     * @param listing The Listing of the product that the User wants to rent
     */
    public void createBooking(int userID, Listing listing) {
        if (userID == listing.getUserId()) {
            System.out.println("Can't book your own listing!");
        } else if (!listing.getIsAvailable()) {
            System.out.println("Listing not available");
        } else {
            Booking booking = new Booking(userID, listing);
            bookings.add(booking);
        }
    }

    /**
     * Gets all Bookings for logged in User
     * @return An ArrayList containing Booking(s) of the logged in User
     */
    public ArrayList<Booking> getMyBookings() {
        ArrayList<Booking> myBookings = new ArrayList<>();
        uHandler = UserHandler.getInstance();
        for (Booking booking : bookings) {
            if (booking.getUserID() == uHandler.getUserID()) {
                myBookings.add(booking);
            }
        }
        return myBookings;
    }

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

    @Override
    public void deleteCompletedBookings() {
        ArrayList<Booking> toRemove = new ArrayList<>();
        for (Booking b: bookings) {
            if (b.getIsToBeRemoved()) {
                toRemove.add(b);
            }
        }
        bookings.removeAll(toRemove);
    }
}