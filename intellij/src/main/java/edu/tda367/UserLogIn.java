package edu.tda367;

import java.util.List;

public class UserLogIn {

    List<User> users;
    private boolean isAuthenticated;
    private User loggedInUser;

    public UserLogIn (){
        //TODO implement how list of users gets populated.
        isAuthenticated = false;

    }
    public boolean logIn (String userName, String password) {

        for (User user : users) {
            String uName = user.getUserName();
            String uPassword = user.getPassword();
            if (uName.equals(userName) && uPassword.equals(password)) {
                isAuthenticated = true;
                loggedInUser = user;
                return true; //login successful
            }
        }
        return false; //login unsuccessful
    }

    public User getLoggedInUser() {
        if(isAuthenticated) {
            return loggedInUser;
        }
        return null; //very dangerous, check for other implementations in the future
    }
}
