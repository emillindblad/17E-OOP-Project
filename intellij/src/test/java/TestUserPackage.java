import edu.tda367.UserPackage.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestUserPackage {

    UserHandler uHandler;
    @Before
    public void UserInit () {
        uHandler = UserHandler.getInstance();
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
    public void SecondUserLogInTest () { //need to log out before another one can log in
        assertFalse(uHandler.logIn("eblad", "eblad123"));
        uHandler.logOut();
        assertTrue(uHandler.getLoggedInUser() == null);
        assertTrue(uHandler.logIn("eblad", "eblad123"));
    }

    @Test
    public void getInstanceTest () {
        UserHandler secondHandler = UserHandler.getInstance();
        assertTrue(secondHandler == uHandler);
        secondHandler.getLoggedInUser().equals(uHandler.getLoggedInUser());
    }


}
