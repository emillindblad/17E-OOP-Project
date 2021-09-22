package edu.tda367.Listing;

import edu.tda367.JSON.JSONReader;
import edu.tda367.JSON.JSONWriter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ListingHandler is an interface for interacting with Listings and their associated products and categories.
 * @author Emil Lindblad
 */
public class ListingHandler {
    private final ArrayList<Listing> listings;
    private final ArrayList<Category> categories;

    /**
     * Constructor, creates lists of categories and listings
     */
    public ListingHandler() {
        //TODO Implement database
        categories = new ArrayList<Category>();
        listings = getSavedListings();
    }

    /**
     * Getter for Categories
     * @return An ArrayList af all available categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * Getter for Listings
     * @return An ArrayList af all current listings
     */
    public ArrayList<Listing> getListings() {
        return listings;
    }

    //TODO Is this the best way to do this?
    public Listing getListing(Listing listing) {
        return listings.get(listings.indexOf(listing));
        //Why no listing.get(listing)?
    }

    /**
     * Removes the specified listing form the ArrayList and returns it
     * @param listing
     * @return The removed listing
     */
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


