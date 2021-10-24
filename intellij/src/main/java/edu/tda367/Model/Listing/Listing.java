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
    private long availability;
    private String listingState = "AVALIABLE";
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
        return evalState().getStatusText();
    }

    /**
     * Getter for the text on an associated button
     * @return String of the text on the button
     */
    @Override
    public String getButtonText() {
        return evalState().getButtonText();
    }

    /**
     * Advances state of Listing, according to state pattern.
     */
    @Override
    public void advanceState() {
        ListingState state = evalState().advanceListingState();
        listingState = state.toString();
        System.out.println("Advancing ListingState" + state);
    }

    public boolean getIsAvailable() {
        return evalState().getIsAvailable();
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
     * Returns whether booking should be advancing state based on listing state
     * @return boolean if booking should advance state
     */
    public boolean getUpdateBookingState() {
        return evalState().getAdvanceBookingState();
	}

    private ListingState evalState() {
        ListingState state;
        switch (listingState) {
            case "BOOKING_SENT":
                state = new BookingSent();
                break;
            case "BOOKING_ACCEPTED":
                state = new BookingAccepted();
                break;
            case "RETURNED":
                state = new Returned();
                break;
            case "UNAVAILABLE":
                state = new Unavailable();
                break;
            default:
                state = new Available();
        }
        return state;
    }

}
