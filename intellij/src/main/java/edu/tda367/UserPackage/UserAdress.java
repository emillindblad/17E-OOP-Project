package edu.tda367.UserPackage;

public class UserAdress {
    private String streetName;
    private String city;
    private int zipCode;
    private String country;

    public UserAdress(String streetName, String city, int zipCode, String country) {
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public UserAdress(String streetName, String city, int zipCode) {
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
        this.country = "Sweden";
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getStreetName() {
        return streetName;
    }

    public String printableAdress (){
        return streetName + "/n" + zipCode + " " + city + "/n" + country;
    }
}
