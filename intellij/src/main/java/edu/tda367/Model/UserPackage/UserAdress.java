package edu.tda367.Model.UserPackage;

/**
 * Class to hold information about an adress.
 *
 * @author Sebastian Kvaldén
 * @version 1.0
 * @since 2021-09-16
 */
 public class UserAdress {
    private String streetName;
    private String city;
    private int zipCode;
    private String country;

    /**
     * Constructor
     * @param streetName
     * @param city
     * @param zipCode
     * @param country
     */
    public UserAdress(String streetName, String city, int zipCode, String country) {
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Constructor with default country set to "Sweden"
     * @param streetName
     * @param city
     * @param zipCode
     */
    public UserAdress(String streetName, String city, int zipCode) {
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
        this.country = "Sweden";
    }

    /**
     *
     * @return City in current adress.
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return Zip-code in current adress.
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     *
     * @return Country in current adress.
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return Street name in current adress.
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     *
     * @return Printable version of adress.
     */
    public String toString (){
        return streetName + "/n" + zipCode + " " + city + "/n" + country;
    }
}
