import edu.tda367.Booking.BookingHandler;
import edu.tda367.Listing.Category;
import edu.tda367.Listing.Listing;
import edu.tda367.Listing.ListingHandler;
import edu.tda367.UserPackage.User;
import edu.tda367.UserPackage.UserHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

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
        listingHandler = new ListingHandler();
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
        bookingHandler = new BookingHandler();
        initSize = bookingHandler.getBookings().size();
        bookingHandler.createBooking(user, userID, listing);
    }

    @AfterClass
    public static void clean() {
        userHandler.logOut();
        listingHandler.removeListing(listing);
        userHandler.removeUser("password", "test");
        userHandler.writeUsers();
    }

    @Test
    public void testBooking() {
        // Create booking - "Sebastian" tries books his own Listing
        int size = bookingHandler.getBookings().size();
        assertTrue(size == initSize + 1); // One new booking should have been created at this point
        bookingHandler.createBooking(user, userID, secondListing);
        assertTrue(bookingHandler.getBookings().size() == size); // No new booking, size remains same
    }

    @Test
    public void testAdvanceBookingState(){


        /* TODO: Rewrite test using bookingHandler
        BookingState before = booking.getBookingState();
        booking.advanceBookingState();
        assertFalse(booking.getBookingState() == before);
        */

    }






}
