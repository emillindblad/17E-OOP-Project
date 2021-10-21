package edu.tda367;

import edu.tda367.Model.Booking.Booking;
import edu.tda367.Model.Booking.BookingHandler;
import edu.tda367.Model.Listing.Category;
import edu.tda367.Model.Listing.Listing;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.User;
import edu.tda367.Model.UserPackage.UserHandler;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TestBookingHandler {
    static BookingHandler bookingHandler;
    static User user;
    static int userID;
    static int initSize;
    static UserHandler userHandler;
    static Listing listing;
    static Listing secondListing;
    static Listing thirdlisting;
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
        userHandler.logOut();
        userHandler.logIn("abc", "test");
        //Setup Listing
        listingHandler = ListingHandler.getInstance();
        testCat = new Category("Övrigt");
        startDate = LocalDateTime.of(2021,9,10,9,0);
        endDate = LocalDateTime.of(2021,9,11,10,30);
        //Create first Listing - Belongs to "Emil"
        listing = listingHandler.createListing("TestPRIT Grill",testCat,"Big grill",userHandler.getUserID(),1337,startDate,endDate);
        thirdlisting = listingHandler.createListing("Test Bajs", testCat, "Big poop", userHandler.getUserID(), 420, startDate, endDate);
        //Login second user
        userHandler.logOut();
        userHandler.logIn("def", "test");
        //Create second listing - Belongs to "Sebastian"
        secondListing = listingHandler.createListing("TestPRIT Bimot",testCat,"Big boi",userHandler.getUserID(),69,startDate,endDate);

        // Create booking - "Sebastian" books "Emil's" Listing
        bookingHandler = BookingHandler.getInstance();
        initSize = bookingHandler.getMyBookings().size();
        bookingHandler.createBooking(userHandler.getUserID(), listing);
        bookingHandler.createBooking(userHandler.getUserID(), thirdlisting);
    }

    @Test
    public void testBooking() {
        int size = bookingHandler.getMyBookings().size();
        assertEquals(size, initSize + 2); // Two new bookings should have been created at this point

        // Create booking - "Sebastian" tries books his own Listing
        bookingHandler.createBooking(userHandler.getUserID(), secondListing);
        assertEquals(bookingHandler.getMyBookings().size(), size); // No new booking, size remains same

        Booking booking = bookingHandler.getMyBookings().get(0);
        assertFalse(booking.getClickable());
        assertEquals("TestPRIT Grill", booking.getProductName());
        assertEquals(1337, booking.getPrice());
        assertEquals("Övrigt", booking.getCategoryName());
        assertEquals("", booking.getImageName());
        assertEquals(listing, booking.getListing());
    }

    @Test
    public void testAdvanceBookingState(){

        Booking myBooking = bookingHandler.getMyBookings().get(1);
        // Get BookingState from last booking in list (test booking)
        assertEquals("Förfrågan skickad", myBooking.getStatusText());
        assertEquals("", myBooking.getButtonText());
        myBooking.advanceState();
        assertEquals("Förfrågan godkänd", myBooking.getStatusText());
        assertEquals("Betala", myBooking.getButtonText());
        myBooking.advanceState();
        myBooking.advanceState();
        assertEquals("Vara tillbakalämnad", myBooking.getStatusText());
        assertEquals("", myBooking.getButtonText());
        myBooking.advanceState();
        assertEquals("Tillbakalämnande godkänt", myBooking.getStatusText());
        assertEquals("Ta bort", myBooking.getButtonText());
        myBooking.advanceState();
        assertEquals("Borttagen!", myBooking.getStatusText());
        assertEquals("", myBooking.getButtonText());
        myBooking.advanceState();
        assertEquals("Borttagen!", myBooking.getStatusText());
        assertEquals("", myBooking.getButtonText());
    }

    @Test
    public void testDeleteRemovableBookings() {
        userHandler.logOut();
        userHandler.logIn("abc", "test");

        // State checker test
        bookingHandler.createBooking(userHandler.getUserID(), secondListing);
        Booking myBooking = bookingHandler.getMyBookings().get(0);

        myBooking.advanceState();
        bookingHandler.deleteCompletedBookings();
        assertEquals(bookingHandler.getMyBookings().size(), 1); // Should remain, cannot delete ongoing Booking
        myBooking.advanceState();
        bookingHandler.deleteCompletedBookings();
        assertEquals(bookingHandler.getMyBookings().size(), 1);
        myBooking.advanceState();
        bookingHandler.deleteCompletedBookings();
        assertEquals(bookingHandler.getMyBookings().size(), 1);
        myBooking.advanceState();               // Can now be removed since state is DONE
        bookingHandler.deleteCompletedBookings();
        assertEquals(bookingHandler.getMyBookings().size(), 1);
        myBooking.advanceState();
        bookingHandler.deleteCompletedBookings();
        assertEquals(bookingHandler.getMyBookings().size(), 0);

        //cleanup
        userHandler.logOut();
        userHandler.logIn("def", "test");
    }
}
