package edu.tda367;

import edu.tda367.Model.Listing.*;
import edu.tda367.Model.UserPackage.UserHandler;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestListing {
    static String[] dummyData = {"Test-Name","Test-Desc","100","Övrigt","DummyPath"};
    static String[] dummyData2 = {"sortingTest","hitta grill","100","Elektronik","DummyPath"};
    static Category testCat;
    static LocalDateTime startDate;
    static LocalDateTime endDate;
    static ListingHandler handler;
    static Listing testListing;
    static Listing secondTestListing;

    static Listing stringListing;
    static int dbSize;


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
            assertTrue(handler.getListingFromKey(key).getIsAvailable());
        }
    }

    @Test
    public void testGetListingData() {
        String[] data = {"Test Name","Test Desc","69","Övrigt","DummyImgPath"};
        Listing listing = handler.createListing(data,dummyId);
        String[] getData = handler.getListingData(listing.getListingId());
        assertTrue(Arrays.equals(getData,new String[] {listing.getListingId(),"Test Name","Övrigt","Test Desc","69","AVALIABLE","DummyImgPath"}));
    }

    @Test
    public void testCreateListingFromString() { //Also tests getListingsAsList()
        String[] formData = {"Test Name","Test Desc","69","Övrigt","DummyImgPath"};
        stringListing = handler.createListing(formData,69);
        assertTrue(handler.getListingsAsList().contains(stringListing));
    }

    @Test
    public void testSortingBySearch() {
        Listing sortListing = handler.createListing(dummyData2, dummyId);
        String search = "hitta grill";
        ArrayList<String> keys = ListingSorter.sortBySearchWord(search, handler.getListings());
        assertTrue(handler.getListingFromKey(keys.get(0)).equals(sortListing));
    }

    @Test
    public void testSortKeys() {
        String[] formData = {"Test Name","Testing-sorting","69","Övrigt","DummyImgPath"};
        Listing sortListing = handler.createListing(formData, dummyId);
        ArrayList<String> keys = handler.getSortedKeys("Testing-sorting");
        Listing listing = handler.getListingFromKey(keys.get(0));
        assertTrue(listing.getProduct().getDescription().equals("Testing-sorting"));
    }

    @Test
    public void testGettersAndSetters() {
        String[] formData = {"Test Name","Test Desc","69","Övrigt","DummyImgPath"};
        Listing listing = handler.createListing(formData,69);
        listing.setFileName("Foobar");
        assertTrue(listing.getFileName().equals("Foobar"));
        assertTrue(listing.getImageName().equals("Foobar"));
        assertTrue(listing.getListingCategory().getCategoryName().equals("Övrigt"));
        assertTrue(listing.getProductName().equals("Test Name"));
        assertTrue(listing.getCategoryName().equals("Övrigt"));

        listing.setPrice(420);
        assertTrue(listing.getPrice()==420);

        listing.setDesc("Foobar");
        assertTrue(listing.getProduct().getDescription().equals("Foobar"));
        assertTrue(listing.getStatusText().equals("Tillgänglig"));
        assertTrue(listing.getButtonText().equals(""));
        assertTrue(listing.getClickable());
        assertTrue(listing.getListing().equals(listing));
    }


}
