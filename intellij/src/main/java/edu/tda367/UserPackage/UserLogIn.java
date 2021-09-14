package edu.tda367.UserPackage;

import java.util.List;

public class UserLogIn {
    //TODO implement how/when users-list gets saved to database
    private List<User> users;
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

    public void createUser( String firstName, String lastName, String phoneNumber, String userName, String password, String bankAccount) {
        User user = new User (firstName, lastName, phoneNumber, userName, password, bankAccount);
        users.add(user);
    }
}
