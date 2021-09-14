package edu.tda367.UserPackage;

 class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserAdress userAdress;
    private int userID;  //TODO implement in constructor

    //for login
    private String userName;
    private String password;

    // proof of concept, needs more information irl
    private String bankAccount;


      User(String firstName, String lastName, String phoneNumber, UserAdress userAdress, String userName, String password, String bankAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userAdress = userAdress;
        this.userName = userName;
        this.password = password;
        this.bankAccount = bankAccount;
    }

      User(String firstName, String lastName, String phoneNumber, String userName, String password, String bankAccount) {
         this.firstName = firstName;
         this.lastName = lastName;
         this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
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

    String getBankAccount() {
        return bankAccount;
    }


}


