import edu.tda367.Booking.Booking;
import edu.tda367.Booking.BookingState;
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
    static Booking booking;
    static User user;
    static int userID;
    static UserHandler userHandler;
    static Listing listing;
    static ListingHandler listingHandler;
    static Category testCat;
    static LocalDateTime startDate;
    static LocalDateTime endDate;


    @BeforeClass
    public static void setup() {
        //Setup User
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
        listing = listingHandler.createListing("TestPRIT Grill",testCat,"Big grill",userID,1337,startDate,endDate);

        //Login second user
        userHandler.logOut();
        userHandler.logIn("def", "test");
        user = userHandler.getLoggedInUser();
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
        booking = new Booking(user, listing);
        assertTrue(booking.getBookingState().equals(BookingState.PENDING));
    }






}
