package edu.tda367;

import edu.tda367.Model.UserPackage.*;
import org.junit.*;

import static org.junit.Assert.*;


public class TestUserPackage {

    static UserHandler uHandler;
    @BeforeClass
    public static void UserInit () {
        uHandler = UserHandler.getInstance();
        uHandler.createUser("Emil", "Lindblad", "0734111337","abc", "test", "123456789" );
        uHandler.createUser("Sebastian", "Kvalden", "0734111337","def", "test", "987654321" );
    }

    @AfterClass
    public static void removeUsers() {
        uHandler.removeUser("password", "test");
        uHandler.writeUsers();
    }

    @Test
    public void UserLogInTest (){
        assertFalse(uHandler.logIn("kvalle", "kvalle"));
        assertTrue(uHandler.getLoggedInUser() == (null));
        assertTrue(uHandler.logIn("abc", "test"));
        assertTrue(uHandler.getLoggedInUser().getFirstName().equals("Emil"));

    }


    @Test
    public void SecondUserLogInTest () { //need to log out before another one can log in
        assertFalse(uHandler.logIn("eblad", "eblad123"));
        uHandler.logOut();
        assertTrue(uHandler.getLoggedInUser() == null);
        assertTrue(uHandler.logIn("def", "test"));
    }

    @Test
    public void getInstanceTest () {
        UserHandler secondHandler = UserHandler.getInstance();
        assertTrue(secondHandler == uHandler);
        secondHandler.getLoggedInUser().equals(uHandler.getLoggedInUser());
    }

    @Test
    public void addAdressAndSaveTest() {
        uHandler.setLoggedInUserAdress("Kemivagen 7B", "Goteborg", "41258", "Sverige");
        uHandler.writeUsers();
        assertTrue(uHandler.getLoggedInUser().getUserAdress().getStreetName().equals("Kemivagen 7B"));
    }

}
