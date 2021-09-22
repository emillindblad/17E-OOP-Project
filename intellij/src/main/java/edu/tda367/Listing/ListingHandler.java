package edu.tda367.Listing;

import edu.tda367.JSON.JSONReader;
import edu.tda367.JSON.JSONWriter;
import edu.tda367.UserPackage.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListingHandler {
    private final ArrayList<Listing> listings;
    private final ArrayList<Category> categories;

    public ListingHandler() {
        //TODO Implement database
        categories = new ArrayList<Category>();
        listings = getSavedListings();
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

    public Listing removeListing(Listing listing) {//TODO Maybe not necessary to return removed listing, breaks CQS.
        listings.remove(listing);
        return listing;
    }

    public Listing createListing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate) {
        Listing listing = new Listing(prodName, prodCat,prodDesc,userId,price,startDate,endDate);
        listings.add(listing);
        return listing;
    }

    private ArrayList<Listing> getSavedListings() {
        ArrayList<Listing> listingstmp = new ArrayList<>();
        JSONReader reader = new JSONReader();
        List<Listing> savedUsers = reader.read(Listing[].class, "listings");
        savedUsers.forEach(l -> listingstmp.add(l));
        return listingstmp;
    }

    public void writeListings() {
        JSONWriter writer = new JSONWriter();
        writer.write(listings, "listings");
    }

}


