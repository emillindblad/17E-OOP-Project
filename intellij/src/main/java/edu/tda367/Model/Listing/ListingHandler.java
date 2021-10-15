package edu.tda367.Model.Listing;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;
import edu.tda367.Model.UserPackage.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.HashMap;
=======
>>>>>>> origin/master
import java.util.List;

/**
 * ListingHandler is a class designed for interacting with Listings and their associated products and categories.
 * @author Emil Lindblad
 */
public class ListingHandler {
    private static ListingHandler instance;
    private final HashMap<Integer,Listing> listings;
    private final ArrayList<Category> categories;

    /**
     * Private Constructor, for limiting class to one instance.
     */
    private ListingHandler() {
        categories = new ArrayList<Category>(); //TODO Implement database?
        populateCategoryList();
        listings = getSavedListings();
    }

    private void populateCategoryList (){
        categories.add(new Category("Bilar"));
        categories.add(new Category("Båtar"));
        categories.add(new Category("Släpkärror"));
        categories.add(new Category("Maskiner"));
        categories.add(new Category("Elektronik"));
        categories.add(new Category("Möbler"));
        categories.add(new Category("Fritidsutrustning"));
        categories.add(new Category("Övrigt"));

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


    public Listing getListingByListingId(String listingId) {
        for(Listing listing : listings) {
            if(listing.getListingId().equals(listingId))
            {
                return listing;
            }
        }
        return listings.get(0);
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
    public HashMap<Integer, Listing> getListings() {
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

    public ArrayList<Integer> getMyListingIds(int userId) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Listing listing : listings) {

        }
        return ids;
    }

    public Listing getListingByProductName(String productName) {
        System.out.println(productName);
        for(Listing listing : listings) {
            System.out.println("listing name " + listing.getProduct().getProdName());
            System.out.println(listing.getProduct().getProdName().equals(productName));
            if(listing.getProduct().getProdName().equals(productName))
            {
                return listing;
            }
        }
        return listings.get(0);
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

    public void sortListings (String sortBy) {
        System.out.println("handler started");
        System.out.println("Befor sorted:" + listings.get(0).getProduct().getProdName());
        ListingSorter.sortBySearchWord(sortBy, listings);
        System.out.println("after sorted:" + listings.get(0).getProduct().getProdName());
        System.out.println("handler done");
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
    private HashMap<Integer, Listing> getSavedListings() {
        HashMap<Integer, Listing> listingstmp = new HashMap<>();
        JSONReader reader = new JSONReader();
        List<Listing> savedUsers = reader.read(Listing[].class, "listings");

        savedUsers.forEach(l ->
        {
            if (l == null) {
                System.out.println("null object in json file");
            } else {
                listingstmp.put(l.getListingID(),l);
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


