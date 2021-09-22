import edu.tda367.Listing.*;
import org.junit.After;
import org.junit.Before;
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
    static Listing testListing;

    @BeforeClass
    public static void setup() {
        handler = new ListingHandler();
        testCat = new Category("Test category");
        startDate = LocalDateTime.of(2021,9,10,9,0);
        endDate = LocalDateTime.of(2021,9,11,10,30);
        testListing = handler.createListing("TestPRIT Grill",testCat,"Big grill",69,1337,startDate,endDate);
    }

    @Test
    public void testHandlerCreate() {
        int initSize = handler.getListings().size(); //Get current size, only test templist
        handler.createListing("Another one",testCat,"lil grill",123,420,startDate,endDate);
        assertTrue(handler.getListings().size()==initSize+1);
    }

    @Test
    public void testHandlerDelete() {
        int initSize = handler.getListings().size(); //Get current size, only test templist
        Listing removedListing = handler.removeListing(testListing);
        assertEquals(removedListing, testListing);
        assertTrue(handler.getListings().size()==initSize-1);
    }

    @After
    public void printList() {
        System.out.println(handler.getListings());
    }



    @Test
    public void testDuration() {
        long duration = ChronoUnit.HOURS.between(startDate,endDate);
        assertTrue(duration==25);
    }

}
