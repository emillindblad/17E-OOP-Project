package edu.tda367.UserPackage;

import java.util.Random;

public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserAdress userAdress;
    private int userID;

    //for login
    private String userName;
    private String password;

    // proof of concept, needs more information irl
    private String bankAccount;


      User(String firstName, String lastName, String phoneNumber, UserAdress userAdress, String userName, String password, String bankAccount, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userAdress = userAdress;
        this.userName = userName;
        this.password = password;
        this.bankAccount = bankAccount;
        this.userID = userID;

    }

      User(String firstName, String lastName, String phoneNumber, String userName, String password, String bankAccount, int userID) {
         this.firstName = firstName;
         this.lastName = lastName;
         this.phoneNumber = phoneNumber;
         this.userName = userName;
         this.password = password;
         this.bankAccount = bankAccount;
         this.userID = userID;
     }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserAdress getUserAdress() {
        return userAdress;
    }


    void setUserAdress (UserAdress adress) {
          this.userAdress = adress;
    }

     void setFirstName(String firstName) {
        this.firstName = firstName;
    }

     void setLastName(String lastName) {
        this.lastName = lastName;
    }

     void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

     void setPassword(String password) {
        this.password = password;
    }

     void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String toString (){
          return "First Name: " + firstName + "\n" +
                  "Last Name: " + lastName + "\n" +
                  "Phone Number: " + phoneNumber + "\n" +
                  "Adress: " + userAdress.toString() + "\n" +
                  "User ID: " + userID + "\n";
    }

    public String getUserName() {
        return userName;
    }



    public String getPassword() {
        return password;
    }

    int getUserID() { return userID;}


    public String getBankAccount() {
        return bankAccount;
    }


}


