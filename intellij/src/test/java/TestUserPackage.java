import edu.tda367.UserPackage.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestUserPackage {

    UserHandler uHandler;
    @Before
    public void UserInit () {
        uHandler = new UserHandler();
        uHandler.createUser("Emil", "Lindblad", "0734111337","eblad", "eblad123", "123456789" );
        uHandler.createUser("Sebastian", "Kvaldén", "0734111337","kvalle", "kvalle123", "987654321" );
    }

    @Test
    public void UserLogInTest (){
        assertFalse(uHandler.logIn("kvalle", "kvalle"));
        assertTrue(uHandler.getLoggedInUser() == (null));
        assertTrue(uHandler.logIn("kvalle", "kvalle123"));
        assertTrue(uHandler.getLoggedInUser().getFirstName().equals("Sebastian"));

    }

    @Test
    public void SecondUserLogInTest () {
        assertFalse(uHandler.logIn("kvalle", "kvalle"));
    }


}
