package edu.tda367.Model.Listing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

/**
* A class representing a Listing of a product
* @author Emil Lindblad
*/
public class Listing {
    private final String listingId;
    private Product product;
    private int userId;
    private int price;
    //private Booking booking;
    private long availability;
    private ListingState listingState;
    private String fileName;

    Listing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate) {
        this.listingId = generateListingId();
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.listingState = ListingState.AVALIBLE; //Defaults to AVALIABLE now

    }

    Listing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate, String fileName) {
        this.listingId = generateListingId();
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.listingState = ListingState.AVALIBLE; //Defaults to AVALIABLE now
        this.fileName = fileName;

    }

    public String getFileName() {
        return this.fileName;
    }

    //TODO Listings, bridge pattern for getting users listings
    //
    private String generateListingId() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
        String id = generator.generate(12);
        System.out.println(id);
        return id;
    }

    public long setAvailability(LocalDateTime startDate, LocalDateTime endDate) {
        availability = ChronoUnit.HOURS.between(startDate,endDate);
        return availability;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "product=" + product +
                ", price=" + price +
                ", availability=" + availability +
                ", listingState=" + listingState +
                '}';
    }

    public String getListingId() {
        return this.listingId;
    }

    public Category getListingCategory() {
        return product.getProdCategory();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ListingState getListingState() {
        return listingState;
    }

    public void setListingState(ListingState listingState) {
        this.listingState = listingState;
    }
}
