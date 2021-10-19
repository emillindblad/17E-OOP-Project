package edu.tda367;

import edu.tda367.Model.Listing.*;
import edu.tda367.Model.UserPackage.UserHandler;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

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
        testListing = handler.createListing(dummyData,dummyId);
        testCat = new Category("Övrigt");
    }

    @Before
    public void dbSize() {
        dbSize = handler.getListings().size();
    }


    @Test
    public void testHandlerCreate() {
        secondTestListing = handler.createListing("Another one",testCat,"lil grill",123,420,startDate,endDate);
        assertTrue(handler.getListings().size()==dbSize+1);
    }

    @Test
    public void testHandlerDelete() {
        int initSize = handler.getListingsAsList().size();
        String[] data = {"Foobar","Test Desc","69","Övrigt","DummyImgPath"};
        Listing listing = handler.createListing(data, dummyId);
        handler.removeListing(listing);
        assertTrue(handler.getListings().size()==initSize);
    }

    @Test
    public void testDuration() {
        long duration = ChronoUnit.HOURS.between(startDate,endDate);
        assertTrue(duration==25);
    }

    @Test
    public void testGetCategories() {
       ArrayList<Category> categories = handler.getCategories();
       assertTrue(categories.size()!=0);
    }

    @Test
    public void testGetCategoryNames() {
        ArrayList<String> names = handler.getCategoryNames();
        assertTrue(names.contains(testCat.getCategoryName()));
    }

    @Test
    public void testGetAvailableListingIds() {
        ArrayList<String> availableListingKeys = handler.getAvailableListingKeys();
        for (String key : availableListingKeys) {
            assertTrue(handler.getListingFromKey(key).getListingState().equals(ListingState.AVAILABLE));
        }
    }

    @Test
    public void testGetListingData() {
        String[] data = {"Test Name","Test Desc","69","Övrigt","DummyImgPath"};
        Listing listing = handler.createListing(data,dummyId);
        String[] getData = handler.getListingData(listing.getListingId());
        assertTrue(Arrays.equals(getData,new String[] {listing.getListingId(),"Test Name","Övrigt","Test Desc","69","AVAILABLE"}));
    }

    @Test
    public void testCreateListingFromString() { //Also tests getListingsAsList()
        String[] formData = {"Test Name","Test Desc","69","Övrigt","DummyImgPath"};
        stringListing = handler.createListing(formData,69);
        assertTrue(handler.getListingsAsList().contains(stringListing));
    }

    @Test public void testRemoveListingWithId() {

    }

    //@Test
    public void testSortingBySearch() {
        System.out.println(handler.getListingsAsList().toString());
        Listing sortListing = handler.createListing("SortingTest",testCat,"hitta grill",127,420,startDate,endDate);
        String search = "hitta grill";
        ListingSorter.sortBySearchWord(search, handler.getListingsAsList());
        System.out.println("AHA");
        System.out.println(handler.getListingsAsList().get(0).getProduct().getProdName());
        assertTrue(handler.getListingsAsList().get(0).equals(sortListing));
    }

}
