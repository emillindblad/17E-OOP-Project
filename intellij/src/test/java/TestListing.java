import edu.tda367.Listing.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestListing {
    private Category testCat;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ListingHandler handler;

    @Before
    public void setup() {
        this.handler = new ListingHandler();
        this.testCat = new Category("Test category");
        this.startDate = LocalDateTime.of(2021,9,10,9,0);
        this.endDate = LocalDateTime.of(2021,9,11,10,30);
    }

    @Test
    public void testHandler() {
        handler.createListing("PRIT Grill",testCat,"Big grill",69,1337,startDate,endDate);
        assertTrue(handler.getListings().size()==1);
    }

    @Test
    public void testConstructor() {
        Listing testListing = new Listing("PRIT Grill",testCat,"Big grill",69,1337,startDate,endDate);
        assertTrue(testListing.toString().equals(testListing.toString()));
        assertTrue(testListing.getOrderSate()==ListingState.AVALIBLE);
    }

    @Test
    public void testDuration() {
        long duration = ChronoUnit.HOURS.between(startDate,endDate);
        assertTrue(duration==25);
    }
}
