package edu.tda367.UserPackage;

import java.util.HashMap;
import java.util.Random;

import edu.tda367.JSON.JSONReader;
import edu.tda367.JSON.JSONWriter;

import java.util.ArrayList;
import java.util.List;


/**
 *UserHandler is a handler to manage Users and functions connected to them. Such as log in/out and getting user data.
 * UserHandler is constructed as a singleton.
 *
 * @author Sebastian Kvaldén
 * @version 1.0
 * @since 2021-09-16
 */
public final class UserHandler {

    private static UserHandler handler;
    private final HashMap <Integer, User> users;
    private boolean isAuthenticated;
    private User loggedInUser;

    private UserHandler (){
    /**
     * Private constructor due to singleton pattern which cannot be accessed from client code.
     */
        isAuthenticated = false;
        users = getSavedUsers();
    }


    /**
     * Used instead of constructor
     * @return The singleton instance of the UserHandler
     */
    public static UserHandler getInstance () {
        if (handler == null) {
            handler = new UserHandler();
        }
        return handler;
    }

    /**
     * Method to log in as a user.
     * @param userName Current users username.
     * @param password Current users password
     * @return  "True" if username and password is correct, aswell as no other user is signed in at the moment.
     * Returns False otherwise.
     */
    public boolean logIn (String userName, String password) { //can only logg in if no other user is logged in
        if(!isAuthenticated){
        for (User user : users.values()) {
            String uName = user.getUserName();
            String uPassword = user.getPassword();
            if (uName.equals(userName) && uPassword.equals(password)) {
                isAuthenticated = true;
                loggedInUser = user;
                return true; //login successful
                }
            }
        }
        return false; //login unsuccessful
    }

    /**
     * Current user is logged out.
     */
    public void logOut () {
        if(isAuthenticated) {
            loggedInUser = null;
            isAuthenticated = false;
        }
    }

    /**
     *
     * @return Reference to the logged in user
     */
    public User getLoggedInUser() { //could be changed so all user-calls are chained through this class instead of giving away the user.
        if(isAuthenticated) {
            return loggedInUser;
        }
        return null; //very dangerous, check for other implementations in the future
    }

    /**
     * Set the adress of current user
     * @param streetName
     * @param city
     * @param zipCode
     * @param country
     */
    public void setLoggedInUserAdress (String streetName, String city, int zipCode, String country) {
        if (isAuthenticated) {
            UserAdress adress = new UserAdress(streetName,city, zipCode,country);
            loggedInUser.setUserAdress(adress);
        }
    }

    /**
     * Method to create a user.
     * @param firstName Users first name.
     * @param lastName Users last name.
     * @param phoneNumber Users phonenumber.
     * @param userName Users username, to be used at log-in
     * @param password Users password
     * @param bankAccount Users bank details
     */
    public void createUser( String firstName, String lastName, String phoneNumber, String userName, String password, String bankAccount) {
        int userID = CreateUserID();
        User user = new User (firstName, lastName, phoneNumber, userName, password, bankAccount, userID);
        users.put(user.getUserID(), user);
    }

    private int CreateUserID () {
        int id;
        while (true) {
            Random generator = new Random();
            id = generator.nextInt(99999); //number between 0-99999
            id = id+100000; //makes ID six figures (100000 to 199999)
            if (users.get(id) == null) //checks if ID is in use already
                break;
        }
        return id;
    }

    public HashMap<Integer, User> getSavedUsers() {
        HashMap<Integer, User> userstmp = new HashMap<>();
        JSONReader reader = new JSONReader();
        List<User> savedUsers = reader.read(User[].class, "users");
        savedUsers.forEach(u -> userstmp.put(u.getUserID(),u));
        return userstmp;
    }

    public void writeUsers() {
        JSONWriter writer = new JSONWriter();
        List<User> usersList = new ArrayList<User>();
        for(User u : users.values()) {
            usersList.add(u);
        }
        writer.write(usersList, "users");
    }
//TODO method to access/notify users when their listings are updated, methods to get user info (adress, etc)
}
