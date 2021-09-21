package edu.tda367.UserPackage;

import edu.tda367.JSON.JSONReader;
import edu.tda367.JSON.JSONWriter;

import java.util.ArrayList;
import java.util.List;

public final class UserHandler {

    private static UserHandler handler;
    private ArrayList<User> users;
    private boolean isAuthenticated;
    private User loggedInUser;

    public UserHandler (){
        //TODO implement how list of users gets populated.
        isAuthenticated = false;
        users = getSavedUsers();
    }

    public static UserHandler getInstance () {
        if (handler == null) {
            handler = new UserHandler();
        }
        return handler;
    }

    public boolean logIn (String userName, String password) { //can only logg in if no other user is logged in
        if(!isAuthenticated){
        for (User user : users) {
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

    public void logOut () {
        if(isAuthenticated) {
            loggedInUser = null;
            isAuthenticated = false;
        }
    }

    public User getLoggedInUser() {
        if(isAuthenticated) {
            return loggedInUser;
        }
        return null; //very dangerous, check for other implementations in the future
    }

    public void createUser( String firstName, String lastName, String phoneNumber, String userName, String password, String bankAccount) {
        User user = new User (firstName, lastName, phoneNumber, userName, password, bankAccount);
        users.add(user);
    }

    public ArrayList<User> getSavedUsers() {
        ArrayList<User> userstmp = new ArrayList<>();
        JSONReader reader = new JSONReader();
        List<User> savedUsers = reader.read(User[].class, "users");
        savedUsers.forEach(u -> userstmp.add(u));
        return userstmp;
    }

    public void writeUsers() {
        JSONWriter writer = new JSONWriter();
        writer.write(users, "users");
    }
//TODO method to access/notify users when their listings are updated
}
