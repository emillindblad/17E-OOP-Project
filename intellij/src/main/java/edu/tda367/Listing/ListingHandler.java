package edu.tda367.Listing;

import edu.tda367.JSON.JSONReader;
import edu.tda367.JSON.JSONWriter;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        categories = new ArrayList<Category>(Arrays.asList(new Category("Övrigt"), new Category("Något annat"))); //TODO Implement database?
        listings = getSavedListings();
    }

    /**
     * Getter for Categories
     * @return An ArrayList af all available categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    private Category getCategory(String categoryName) {
        Category category = null;
        for (Category value : categories) {
            if (value.getCategoryName().equals(categoryName)) {
                category = value;
            }
        }
        return category;
    }

    public ArrayList<String> getCategoryNames() {
        ArrayList<String> categoryNames = new ArrayList<>();
        categories.forEach((e) -> categoryNames.add(e.getCategoryName()));
        return categoryNames;
    }

    /**
     * Getter for Listings
     * @return An ArrayList af all current listings
     */
    public ArrayList<Listing> getListings() {
        return listings;
    }

    public ArrayList<Listing> getAvailableListings() {
        ArrayList<Listing> availableListings = new ArrayList<Listing>();
        for(Listing listing : listings)
        {
            if(listing.getOrderState().equals(ListingState.AVALIBLE))
            {
                availableListings.add(listing);
            }

        }
        return availableListings;
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
        Listing listing = new Listing(prodName,prodCat,prodDesc,userId,price,startDate,endDate);
        listings.add(listing);
        return listing;
    }

    public Listing createListingFromString(String[] formData) {
        //Parse data in different method?
        String prodName = formData[0];
        String prodDesc = formData[1];
        int price = Integer.parseInt(formData[2]);
        Category prodCat = getCategory(formData[3]);
        int userId = 69;

        LocalDateTime startDate = LocalDateTime.of(2021,9,10,9,0);
        LocalDateTime endDate = LocalDateTime.of(2021,9,11,10,30);

        Listing listing = new Listing(prodName,prodCat,prodDesc,userId,price,startDate,endDate);
        listings.add(listing);
        System.out.println(listings);
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


