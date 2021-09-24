import edu.tda367.Listing.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import static org.junit.Assert.*;

public class TestListing {
    static Category testCat;
    static LocalDateTime startDate;
    static LocalDateTime endDate;
    static ListingHandler handler;
    static ListingHandler secondHandler;
    static Listing testListing;
    static Listing secondTestListing;
    private static int dbSize;


    @BeforeClass
    public static void setup() {
        handler = new ListingHandler();
        dbSize = handler.getListings().size();
        testCat = new Category("Test category");
        startDate = LocalDateTime.of(2021,9,10,9,0);
        endDate = LocalDateTime.of(2021,9,11,10,30);
        testListing = handler.createListing("TestPRIT Grill",testCat,"Big grill",69,1337,startDate,endDate);
    }

    @AfterClass
    public static void clean() {
        //---- Remove test listings from database ------//
        handler.removeListing(secondTestListing);
        handler.removeListing(testListing);
        handler.writeListings();
    }

    @Test
    public void testHandlerCreate() {
        int initSize = handler.getListings().size(); //Get current size, only test templist
        secondTestListing = handler.createListing("Another one",testCat,"lil grill",123,420,startDate,endDate);
        assertTrue(handler.getListings().size()==initSize+1);
    }

    @Test
    public void testHandlerDelete() {
        int initSize = handler.getListings().size(); //Get current size, only test templist
        Listing removedListing = handler.removeListing(testListing);
        assertEquals(removedListing, testListing);
        assertTrue(handler.getListings().size()==initSize-1);
    }

    @Test
    public void testDuration() {
        long duration = ChronoUnit.HOURS.between(startDate,endDate);
        assertTrue(duration==25);
    }

    @Test
    public void testDatabaseWrite() {
        handler.writeListings();
        secondHandler = new ListingHandler(); // Second handler to simulate startup. Gets saved Listings from database
        ArrayList<Listing> newListings = secondHandler.getListings(); // Needed extra step for some reason...
        assertTrue(newListings.size() == dbSize + 1);
    }
}
