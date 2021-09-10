package edu.tda367;

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

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
