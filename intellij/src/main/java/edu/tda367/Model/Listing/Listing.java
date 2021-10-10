package edu.tda367.Model.Listing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
* A class representing a Listing of a product
* @author Emil Lindblad
*/
public class Listing {
    private Product product;
    private int userId;
    private int price;
    //private Booking booking;
    private long availability;
    private ListingState orderState;

    Listing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate) {
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.orderState = ListingState.AVALIBLE;
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
                ", orderSate=" + orderState +
                '}';
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

    public ListingState getOrderState() {
        return orderState;
    }

    public void setOrderState(ListingState orderState) {
        this.orderState = orderState;
    }
}
