import edu.tda367.UserPackage.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestUserPackage {

    static UserHandler uHandler;
    @BeforeClass
    public static void UserInit () {
        uHandler = UserHandler.getInstance();
        uHandler.createUser("Emil", "Lindblad", "0734111337","abc", "abc123", "123456789" );
        uHandler.createUser("Sebastian", "Kvalden", "0734111337","def", "def123", "987654321" );
    }

    @Test
    public void UserLogInTest (){
        assertFalse(uHandler.logIn("kvalle", "kvalle"));
        assertTrue(uHandler.getLoggedInUser() == (null));
        assertTrue(uHandler.logIn("abc", "abc123"));
        assertTrue(uHandler.getLoggedInUser().getFirstName().equals("Emil"));

    }


    @Test
    public void SecondUserLogInTest () { //need to log out before another one can log in
        assertFalse(uHandler.logIn("eblad", "eblad123"));
        uHandler.logOut();
        assertTrue(uHandler.getLoggedInUser() == null);
        assertTrue(uHandler.logIn("def", "def123"));
    }

    @Test
    public void getInstanceTest () {
        UserHandler secondHandler = UserHandler.getInstance();
        assertTrue(secondHandler == uHandler);
        secondHandler.getLoggedInUser().equals(uHandler.getLoggedInUser());
    }

    @Test
    public void addAdressAndSaveTest() {
        uHandler.setLoggedInUserAdress("Kemivagen 7B", "Goteborg", 41258, "Sverige");
        uHandler.writeUsers();
        assertTrue(uHandler.getLoggedInUser().getUserAdress().getStreetName().equals("Kemivagen 7B"));
    }


}
