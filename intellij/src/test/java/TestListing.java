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

        long duration = ChronoUnit.HOURS.between(startDate,endDate);
        assertTrue(duration==25);
    }
}
