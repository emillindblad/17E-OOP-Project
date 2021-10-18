package edu.tda367;

import edu.tda367.Model.Listing.*;
import edu.tda367.Model.UserPackage.User;
import edu.tda367.Model.UserPackage.UserHandler;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestListing {
    static String[] dummyData = {"Test-Name","Test-Desc","100","Övrigt","DummyPath"};
    static Category testCat;
    static LocalDateTime startDate;
    static LocalDateTime endDate;
    static ListingHandler handler;
    static Listing testListing;
    static Listing secondTestListing;

    static Listing stringListing;
    static int dbSize;

    static Listing thirdTestListing;

    static int dummyId;
    static UserHandler uHandler;


    @BeforeClass
    public static void setup() {
        uHandler = UserHandler.getInstance();
        uHandler.createUser("Test", "Tester", "123456789","test", "test", "123456789" );
        uHandler.logIn("test","test");
        dummyId = uHandler.getUserID();

        startDate = LocalDateTime.of(2021,9,10,9,0);
        endDate = LocalDateTime.of(2021,9,11,10,30);

        handler = ListingHandler.getInstance();
        testListing = handler.createListingFromForm(dummyData,dummyId);
        testCat = new Category("Övrigt");
    }

    @Before
    public void dbSize() {
        dbSize = handler.getListings().size();
    }

    @AfterClass
    public static void clean() {
        //---- Remove test listings from database ------//
        System.out.println("Cleaning up...");
        handler.removeListing(secondTestListing);
        handler.removeListing(testListing);
        handler.removeListing(stringListing);
        handler.writeListings();
        uHandler.removeUser("test","test");
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

    //@Test
    public void testGetAvailableListings() {
        ArrayList<String> availableListings = handler.getAvailableListingKeys();
        System.out.println(availableListings);
        for (String listing : availableListings) {
            //assertTrue(listing.getListingState().equals(ListingState.AVALIBLE));
        }
    }

    @Test
    public void testCreateListingFromString() {
        String[] formData = {"Test Name","Test Desc","69","Övrigt","DummyImgPath"};
        stringListing = handler.createListingFromForm(formData,69);
        //assertTrue(handler.getListings().contains(stringListing));
    }

    //@Test
    public void testSortingBySearch() {
        //List <Listing> listToTest = createProductList();
        System.out.println(handler.getListings().toString());
        thirdTestListing = handler.createListing("SortingTest",testCat,"hitta grill",127,420,startDate,endDate);
        String search = "hitta grill";
        ListingSorter.sortBySearchWord(search, handler.getListingsAsList());
        System.out.println("AHA");
        System.out.println(handler.getListings().get(0).getProduct().getProdName());
        assertTrue(handler.getListings().get(0).getProduct().getProdName().equals("SortingTest"));
    }

}
