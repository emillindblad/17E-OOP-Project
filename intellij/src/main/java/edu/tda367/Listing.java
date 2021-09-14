package edu.tda367;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Listing {
    private Product product;
    private int userId;
    private int price;
    //private Booking booking;
    private long availability;
    private ListingState orderSate;

    public Listing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate) {
        this.product = new Product(prodName, prodCat, prodDesc);
        this.userId = userId;
        this.price = price;
        this.availability = setAvailability(startDate, endDate);
        this.orderSate = ListingState.AVALIBLE;
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
                ", orderSate=" + orderSate +
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

    public ListingState getOrderSate() {
        return orderSate;
    }

    public void setOrderSate(ListingState orderSate) {
        this.orderSate = orderSate;
    }
}
