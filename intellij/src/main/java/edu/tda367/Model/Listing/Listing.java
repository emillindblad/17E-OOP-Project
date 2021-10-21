package edu.tda367.Model.Listing;

import edu.tda367.Model.Listing.ListingStates.Available;
import edu.tda367.Model.ListingStateListener;
import edu.tda367.Model.RentingItemEntry;
import edu.tda367.Model.Listing.ListingStates.ListingState;

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
    private ListingState listingState = new Available();
    private String fileName;
    private ListingStateListener listener;

    Listing(String listingId, String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate, String fileName) {
        this.listingId = listingId;
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String file) {
        this.fileName = file;
    }

    //TODO Listings, bridge pattern for getting users listings
    //

    public long setAvailability(LocalDateTime startDate, LocalDateTime endDate) {
        availability = ChronoUnit.HOURS.between(startDate,endDate);
        return availability;
    }

    public void setListener(ListingStateListener listener) {
        this.listener = listener;
    }

    public long getAvailability() {
        return availability;
    }

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

    public String getListingId() {
        return this.listingId;
    }

    public Category getListingCategory() {
        return product.getProdCategory();
    }

    public Product getProduct() {
        return product;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String getProductName() {
        return getProduct().getProdName();
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String getCategoryName() {
        return getListingCategory().getCategoryName();
    }


    @Override
    public String getStatusText() {
        return listingState.getStatusText();
    }

    @Override
    public String getButtonText() {
        return listingState.getButtonText();
    }

    @Override
    public void advanceState() {
        listingState = listingState.advanceListingState();

        if (listingState.getAdvanceBookingState()) {
            listener.listingStateChangedAction();
        }
    }

    public boolean getIsAvailable() {
        return listingState.getIsAvailable();
    }

    @Override
    public boolean getClickable() {
        return true;
    }

    @Override
    public Listing getListing() {
        return this;
    }

    @Override
    public String getImageName() {
        return getFileName();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        this.product.setDescription(desc);
    }
}
