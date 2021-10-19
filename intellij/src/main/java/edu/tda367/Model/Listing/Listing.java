package edu.tda367.Model.Listing;

import edu.tda367.Model.RentingItemEntry;
import javafx.scene.image.Image;

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

    Listing(String listingId, String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate, String fileName) {
        this.listingId = listingId;
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.listingState = ListingState.AVAILABLE; //Defaults to AVAILABLE now
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    //TODO Listings, bridge pattern for getting users listings
    //

    public long setAvailability(LocalDateTime startDate, LocalDateTime endDate) {
        availability = ChronoUnit.HOURS.between(startDate,endDate);
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

    // TODO images
    @Override
    public Image getImage() {
        return null;
    }

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

    @Override
    public String getButtonText() {
        return switch (listingState) {
            case BOOKING_SENT -> "Acceptera";
            case RETURNED -> "Ja, jag har fått den";
            default -> "";
        };
    }

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

    public ListingState getListingState() {
        return listingState;
    }

    public void setListingState(ListingState listingState) {
        this.listingState = listingState;
    }
}
