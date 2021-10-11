package edu.tda367;

import edu.tda367.Model.Listing.*;
import org.junit.*;

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
    static Listing secondTestListing;
    static Listing stringListing;
    static int dbSize;


    @BeforeClass
    public static void setup() {
        handler = ListingHandler.getInstance();
        testCat = new Category("Övrigt");
        startDate = LocalDateTime.of(2021,9,10,9,0);
        endDate = LocalDateTime.of(2021,9,11,10,30);
        testListing = handler.createListing("TestPRIT Grill",testCat,"Big grill",69,1337,startDate,endDate);
    }

    @Before
    public void dbsize() {
        dbSize = handler.getListings().size();
    }

    @AfterClass
    public static void clean() {
        //---- Remove test listings from database ------//
        handler.removeListing(secondTestListing);
        handler.removeListing(testListing);
        handler.removeListing(stringListing);
        handler.writeListings();
    }

    @Test
    public void testHandlerCreate() {
        secondTestListing = handler.createListing("Another one",testCat,"lil grill",123,420,startDate,endDate);
        assertTrue(handler.getListings().size()==dbSize+1);
    }

    @Test
    public void testHandlerDelete() {
        Listing removedListing = handler.removeListing(testListing);
        assertEquals(removedListing, testListing);
        assertTrue(handler.getListings().size()==dbSize-1);
    }

    @Test
    public void testDuration() {
        long duration = ChronoUnit.HOURS.between(startDate,endDate);
        assertTrue(duration==25);
    }

    @Test
    public void testDatabaseWrite() {
        handler.writeListings();
        ListingHandler secondHandler = ListingHandler.getInstance(); // Second handler to simulate startup. Gets saved Listings from database
        ArrayList<Listing> newListings = secondHandler.getListings(); // Needed extra step for some reason...
        assertTrue(secondHandler.getListings().size() == dbSize);
    }

    //@Test TODO
    public void testGetCategories() {
        assertTrue(handler.getCategories().contains(testCat));
    }

    @Test
    public void testGetCategoryNames() {
        ArrayList<String> names = handler.getCategoryNames();
        assertTrue(names.contains(testCat.getCategoryName()));
    }

    @Test
    public void testGetAvaliableListings() {
        ArrayList<Listing> availableListings = handler.getAvailableListings();
        availableListings.forEach(n -> n.getListingState().equals(ListingState.AVALIBLE));
    }

    @Test
    public void testCreateListingFromString() {
        String[] formData = {"Test Name","Test Desc","69","Övrigt"};
        stringListing = handler.createListingFromString(formData);
        assertTrue(handler.getListings().contains(stringListing));
    }

}
