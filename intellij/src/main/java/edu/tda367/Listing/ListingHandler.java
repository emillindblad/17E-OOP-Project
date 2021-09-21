package edu.tda367.Listing;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListingHandler {
    private ArrayList<Listing> listings;
    private ArrayList<Category> categories;

    public ListingHandler() {
        //TODO Implement database
        //this.listings = db.getListings;
        categories = new ArrayList<Category>();
        listings = new ArrayList<Listing>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }

    //TODO Is this the best way to do this?
    public Listing getListing(Listing listing) {
        return listings.get(listings.indexOf(listing));
        //Why no listing.get(listing)?
    }

    public Listing removeListing(Listing listing) {
        listings.remove(listing);
        return listing;
    }

    public Listing createListing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate) {
        Listing listing = new Listing(prodName, prodCat,prodDesc,userId,price,startDate,endDate);
        listings.add(listing);
        return listing;
    }

}


