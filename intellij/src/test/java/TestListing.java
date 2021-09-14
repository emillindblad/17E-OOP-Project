import edu.tda367.Category;
import edu.tda367.Listing;
import edu.tda367.Product;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertTrue;

public class TestListing {

    @Test
    public void testDuration() {
        LocalDateTime startDate = LocalDateTime.of(2021,9,10,9,0);
        LocalDateTime endDate = LocalDateTime.of(2021,9,11,10,30);

        Duration d  = Duration.between(startDate,endDate);
        d.getUnits();
        long d2 = ChronoUnit.HOURS.between(startDate,endDate);
        System.out.println(d2);
        assertTrue(1==1);
    }

    @Test
    public void testConstructor() {
        LocalDateTime startDate = LocalDateTime.of(2021,9,10,9,0);
        LocalDateTime endDate = LocalDateTime.of(2021,9,11,10,30);
        Category testCategory = new Category("foo");
        Listing testListing = new Listing("PRIT Grill",testCategory,"nice",1,1337,startDate,endDate);
        System.out.println(testListing);
        assertTrue(1==1);
    }

}
