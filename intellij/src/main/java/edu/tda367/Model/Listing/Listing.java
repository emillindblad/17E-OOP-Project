package edu.tda367.Model.Listing;

import edu.tda367.Model.RentingItemEntry;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
* A class representing a Listing of a product
* @author Emil Lindblad
*/
public class Listing implements RentingItemEntry {
    private final String listingId;
    private Product product;
    private int userId;
    private int price;
    //private Booking booking;
    private long availability;
    private ListingState listingState;
    private String fileName;

    /**
     * Constructor for a Listing
     * @param listingId - An unique ID for the listing, also used as a Key when storing Listings in a HashMap
     * @param prodName - The name of the product
     * @param prodCat - The product category, in form of an object
     * @param prodDesc - A description of the product
     * @param userId - The userId of the creator of the listing
     * @param price - The price of renting the product
     * @param startDate - The startdate of the listing
     * @param endDate - The enddate of the listing
     * @param fileName - The file name of the listing image (if any)
     */
    Listing(String listingId, String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate, String fileName) {
        this.listingId = listingId;
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.listingState = ListingState.AVAILABLE; //Defaults to AVAILABLE now
        this.fileName = fileName;
    }

    /**
     * Getter for file name
     * @return The file name of the listing image
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Setter for file name
     * @param file The file name of an image
     */
    public void setFileName(String file) {
        this.fileName = file;
    }

    //Currently calculates the time between two dates. Was supposed to be used for booking during a span of dates and need to check when a Listing was available
    private long setAvailability(LocalDateTime startDate, LocalDateTime endDate) {
        availability = ChronoUnit.HOURS.between(startDate,endDate);
        return availability;
    }

    /**
     * Getter for availability
     * @return The current availability for the listing.
     */
    public long getAvailability() {
        return availability;
    }

    /**
     * Takes the listing and returns it as an Array of strings. Contain its properties.
     * @return An Array of Strings
     */
    String[] toArray() {
        return new String[]{
            this.listingId,
            this.product.getProdName(),
            this.product.getCategoryName(),
            this.product.getDescription(),
            String.valueOf(this.price),
            this.listingState.toString(),
            this.fileName
        };

    }

    /**
     * Allows a listing object to be represented as a String
     * @return String
     */
    @Override
    public String toString() {
        return "Listing{" +
                "product=" + product +
                ", price=" + price +
                ", availability=" + availability +
                ", listingState=" + listingState +
                '}';
    }

    /**
     * Getter for ListingId
     * @return A String of the ListingId
     */
    public String getListingId() {
        return this.listingId;
    }

    /**
     * Getter for product category
     * @return A category object
     */
    public Category getListingCategory() {
        return product.getProdCategory();
    }

    /**
     * Getter for a listings product
     * @return A Product object
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Getter for a Listings userId
     * @return An integer of the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter for a listings product name
     * @return String of the product name
     */
    @Override
    public String getProductName() {
        return getProduct().getProdName();
    }

    /**
     * Getter for a listings price
     * @return An int of the listings price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter for a listings category name
     * @return A String of the listings category name
     */
    @Override
    public String getCategoryName() {
        return getListingCategory().getCategoryName();
    }


    /**
     * Getter for a listings state
     * @return A string of the current state
     */
    @Override
    public String getStatusText() {
        return switch (listingState) {
            case BOOKING_SENT -> "Förfrågan mottagen";
            case BOOKING_ACCEPTED -> "inväntar betalning";
            case UNAVAILABLE -> "betalad och uthyrd";
            case RETURNED -> "Återlämnad";
            default -> "Tillgänglig";
        };
    }

    /**
     * Getter for the text on an associated button
     * @return String of the text on the button
     */
    @Override
    public String getButtonText() {
        return switch (listingState) {
            case BOOKING_SENT -> "Acceptera";
            case RETURNED -> "Ja, jag har fått den";
            default -> "";
        };
    }

    /**
     * Changes the state of a listing
     */
    @Override
    public void advanceState() {
        switch (listingState) {
            case BOOKING_SENT -> listingState = ListingState.BOOKING_ACCEPTED;
            case BOOKING_ACCEPTED -> listingState = ListingState.UNAVAILABLE;
            case UNAVAILABLE -> listingState = ListingState.RETURNED;
            case RETURNED -> listingState = ListingState.AVAILABLE;
            default -> listingState = ListingState.BOOKING_SENT;
        }
    }

    /**
     * Getter if a listing is clickable
     * @return Boolean if listing is clickable
     */
    @Override
    public boolean getClickable() {
        return true;
    }

    /**
     * Getter for a listing
     * @return A listing
     */
    @Override
    public Listing getListing() {
        return this;
    }

    /**
     * Getter for file name
     * @return The file name of the listing image
     */
    @Override
    public String getImageName() {
        return getFileName();
    }

    /**
     * Setter for listing price
     * @param price The new price of a listing
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Setter for a listings products description
     * @param desc The new description
     */
    public void setDesc(String desc) {
        this.product.setDescription(desc);
    }

    /**
     * Getter of the current listing state
     * @return The current listing state
     */
    public ListingState getListingState() {
        return listingState;
    }

    /**
     * Setter for listingstate
     * @param listingState The new listing sate
     */
    public void setListingState(ListingState listingState) {
        this.listingState = listingState;
    }
}
