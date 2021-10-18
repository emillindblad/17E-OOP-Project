package edu.tda367;

import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Booking.BookingState;
import edu.tda367.Model.Listing.Category;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.User;
import edu.tda367.Model.UserPackage.UserHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestBooking {
    static BookingHandler bookingHandler;
    static User user;
    static int userID;
    static int initSize;
    static UserHandler userHandler;
    static Listing listing;
    static Listing secondListing;
    static ListingHandler listingHandler;
    static Category testCat;
    static LocalDateTime startDate;
    static LocalDateTime endDate;


    @BeforeClass
    public static void setup() {
        //Setup Users
        userHandler = UserHandler.getInstance();
        userHandler.createUser("Emil", "Lindblad", "0734111337","abc", "test", "123456789" );
        userHandler.createUser("Sebastian", "Kvalden", "0734111337","def", "test", "987654321" );
        userHandler.logIn("abc", "test");
        userID = userHandler.getUserID();

        //Setup Listing
        listingHandler = ListingHandler.getInstance();
        testCat = new Category("Test category");
        startDate = LocalDateTime.of(2021,9,10,9,0);
        endDate = LocalDateTime.of(2021,9,11,10,30);
        //Create first Listing - Belongs to "Emil"
        listing = listingHandler.createListing("TestPRIT Grill",testCat,"Big grill",userID,1337,startDate,endDate);

        //Login second user
        userHandler.logOut();
        userHandler.logIn("def", "test");
        user = userHandler.getLoggedInUser();
        userID = userHandler.getUserID();
        //Create second listing - Belongs to "Sebastian"
        secondListing = listingHandler.createListing("TestPRIT Bimot",testCat,"Big boi",userID,69,startDate,endDate);

        // Create booking - "Sebastian" books "Emil's" Listing
        bookingHandler = BookingHandler.getInstance();
        initSize = bookingHandler.getMyBookings().size();
        bookingHandler.createBooking(user, userID, listing);
    }

    @AfterClass
    public static void clean() {
        userHandler.logOut();
        listingHandler.removeListing(listing);
        listingHandler.removeListing(secondListing);
        userHandler.removeUser("password", "test");
        userHandler.writeUsers();
    }

    @Test
    public void testBooking() {
        int size = bookingHandler.getMyBookings().size();
        assertEquals(size, initSize + 1); // One new booking should have been created at this point

        // Create booking - "Sebastian" tries books his own Listing
        bookingHandler.createBooking(user, userID, secondListing);
        assertEquals(bookingHandler.getMyBookings().size(), size); // No new booking, size remains same
    }

    @Test
    public void testGetBookedListings(){
        userHandler.logOut(); // Emil logs in
        userHandler.logIn("abc", "test");

        assertEquals(bookingHandler.getMyBookedListings().size(), 1);
        // Cleanup
        userHandler.logOut();
        userHandler.logIn("def", "test");
    }

    @Test
    public void testAdvanceBookingState(){

        // Get BookingState from last booking in list (test booking)
        // Should be Pending
        assertEquals(bookingHandler.getBookingState(initSize), BookingState.PENDING);
        // Advance booking state
        bookingHandler.advanceBookingState(initSize);
        assertEquals(bookingHandler.getBookingState(initSize), BookingState.ACCEPTED);
        // Advance booking state again
        bookingHandler.advanceBookingState(initSize);
        assertEquals(bookingHandler.getBookingState(initSize), BookingState.PAYED);
        // Advance booking state last time, should be unchanged
        bookingHandler.advanceBookingState(initSize);
        assertEquals(bookingHandler.getBookingState(initSize), BookingState.RETURNED);
        bookingHandler.advanceBookingState(initSize);
        assertEquals(bookingHandler.getBookingState(initSize), BookingState.DONE);
        bookingHandler.advanceBookingState(initSize);
        assertEquals(bookingHandler.getBookingState(initSize), BookingState.DONE);

    }

}
