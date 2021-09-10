package edu.tda367;

public class User {

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private UserAdress userAdress;

    //for login
    private String userName;
    private String password;

    // proof of concept, needs more information irl
    private int bankAccount;

    //TODO: Owner and customer classes and implementation

    public User(String firstName, String lastName, int phoneNumber, UserAdress userAdress, String userName, String password, int bankAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userAdress = userAdress;
        this.userName = userName;
        this.password = password;
        this.bankAccount = bankAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public UserAdress getUserAdress() {
        return userAdress;
    }

     String getUserName() {
        return userName;
    }

     String getPassword() {
        return password;
    }

    int getBankAccount() {
        return bankAccount;
    }


}


