import edu.tda367.UserPackage.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserPackageTests {

    @BeforeClass
    public static void UserInit () {
        UserHandler uHandler = new UserHandler();
        uHandler.createUser("Emil", "Lindblad", "0734111337","eblad", "eblad123", "123456789" );
        uHandler.createUser("Sebastian", "Kvaldén", "0734111337","kvalle", "kvalle123", "987654321" );
    }

    @Test
    public void UserLogInTets (UserHandler uHandler){
        assertFalse(uHandler.logIn("kvalle", "kvalle123"));
        assertTrue(uHandler.getLoggedInUser().equals(null));
        assertTrue(uHandler.logIn("kvalle", "kvalle"));
        assertTrue(uHandler.getLoggedInUser().getFirstName().equals("Sebastian"));

    }
}
