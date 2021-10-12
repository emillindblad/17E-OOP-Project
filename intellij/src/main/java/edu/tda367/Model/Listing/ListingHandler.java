package edu.tda367.Model.Listing;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListingHandler is a class designed for interacting with Listings and their associated products and categories.
 * @author Emil Lindblad
 */
public class ListingHandler {
    private static ListingHandler instance;
    private final ArrayList<Listing> listings;
    private final ArrayList<Category> categories;

    /**
     * Private Constructor, for limiting class to one instance.
     */
    private ListingHandler() {
        categories = new ArrayList<Category>(Arrays.asList(new Category("Övrigt"), new Category("Något annat"))); //TODO Implement database?
        listings = getSavedListings();
    }

    /**
     * Instance getter, only allows one intstance of the object to be used.
     * @return The instance of the class.
     */
    public static ListingHandler getInstance() {
        if (instance == null) {
            instance =new ListingHandler();
        }
        return instance;
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

    /**
     * Getter for category names.
     * @return An ArrayList with category names.
     */
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

    /**
     * Getter for Listings where ListingState is Avaliable
     * @return An ArrayList af all current Avaliable listings
     */
    public ArrayList<Listing> getAvailableListings() {
        ArrayList<Listing> availableListings = new ArrayList<Listing>();
        for(Listing listing : listings)
        {
            if(listing.getListingState().equals(ListingState.AVALIBLE))
            {
                availableListings.add(listing);
            }
        }
        return availableListings;
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


    /**
     * Creates a listing from supplied parameters, adds it to the ArrayList of listings, and returns the newly created listing.
     * @param prodName - The name of the product
     * @param prodCat - The product category, in form of an object
     * @param prodDesc - A description of the product
     * @param userId - The userId of the creator of the listing
     * @param price - The price of renting the product
     * @param startDate - The startdate of the listing
     * @param endDate - The enddate of the listing
     * @return listing - The newly created listing
     */
    public Listing createListing(String prodName, Category prodCat, String prodDesc, int userId, int price, LocalDateTime startDate, LocalDateTime endDate) {
        Listing listing = new Listing(prodName,prodCat,prodDesc,userId,price,startDate,endDate);
        listings.add(listing);
        return listing;
    }

    /**
     * Creates a listing from an array with desired data, adds the created listing to the ArrayList of listings, and returns the newly created listing.
     * @param formData - An Array of listing data, all in strings.
     * @return listing - The newly created listing
     */
    public Listing createListingFromString(String[] formData, int userId) {
        //Parse data in different method?
        String prodName = formData[0];
        String prodDesc = formData[1];
        int price = Integer.parseInt(formData[2]);
        Category prodCat = getCategory(formData[3]);

        //Hardcoded values for now
        LocalDateTime startDate = LocalDateTime.of(2021,9,10,9,0);
        LocalDateTime endDate = LocalDateTime.of(2021,9,11,10,30);

        Listing listing = new Listing(prodName,prodCat,prodDesc,userId,price,startDate,endDate);
        listings.add(listing);
        System.out.println(listings);
        return listing;
    }

    /**
     * Gets saved Listings from database
     * @author Erik Larsson
     * @return An ArrayList containing Listing objects
     *
     */
    private ArrayList<Listing> getSavedListings() {
        ArrayList<Listing> listingstmp = new ArrayList<>();
        JSONReader reader = new JSONReader();
        List<Listing> savedUsers = reader.read(Listing[].class, "listings");
        savedUsers.forEach(l ->
        {
            if (l == null) {
                System.out.println("null object in json file");
            } else {
                listingstmp.add(l);
            }
        });
        return listingstmp;
    }

    /**
     * Writes Listings to database
     * @author Erik Larsson
     */
    public void writeListings() {
        JSONWriter writer = new JSONWriter();
        writer.write(listings, "listings");
    }

}


