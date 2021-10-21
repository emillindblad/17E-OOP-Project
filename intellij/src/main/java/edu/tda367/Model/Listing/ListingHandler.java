package edu.tda367.Model.Listing;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;
import edu.tda367.Model.ListingLinker;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ListingHandler is a class designed for interacting with Listings and their associated products and categories.
 * @author Emil Lindblad
 */
public class ListingHandler {
    private static ListingHandler instance;
    private final HashMap<String,Listing> listings;
    private final ArrayList<Category> categories;
    private final ListingLinker linker;

    /**
     * Private Constructor, for limiting class to one instance.
     */
    private ListingHandler() {
        categories = new ArrayList<Category>(); //TODO Implement database?
        populateCategoryList();
        listings = getSavedListings();
        linker = new ListingLinker();
    }

    /**
     * Initializes the list of categories with hard coded values
     */
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

    /**
     * Getter for a specific category
     * @param categoryName The name of the category you want.
     * @return The category object.
     */
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
     * Gets a specific listing and returns it as an array of strings.
     * @param key The key in the hashmap that corresponds to the listing you want.
     * @return An Array of strings with a listings properties.
     */
    public String[] getListingData(String key) {
        return listings.get(key).toArray();
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
     * Getter for all Listings
     * @return A Hashmap af all current listings
     */
    public HashMap<String, Listing> getListings() {
        return listings;
    }

    /**
     * Makes a list of all Listings in the handler
     * @return ArrayList<Listing>
     */
    public ArrayList<Listing> getListingsAsList() {
        return new ArrayList<>(listings.values());
    }

    /**
     * Get a specific listing from the hashmap key (ListingID)
     * @param key Key to the Hashmap of Listings, (ListingID)
     * @return A Listing connected to the key
     */
    public Listing getListingFromKey(String key) {
        return listings.get(key);
    }

    /**
     * Getter for Listing Keys where ListingState is Available
     * @return An ArrayList af all keys which corresponds to a Listing with ListingState as AVAILABLE
     */
    public ArrayList<String> getAvailableListingKeys() {
        ArrayList<String> availableListings = new ArrayList<>();
        listings.forEach(
            (key, listing) -> {
                if (listing.getIsAvailable()) {
                    availableListings.add(key);
                }
            }
        );
        return availableListings;
    }

    /**
     * Getter for Listing Keys where ListingState is Available and belongs to a specific category.
     * @param category Category to find listings in.
     * @return An ArrayList af all keys which corresponds to a Listing with ListingState as AVAILABLE and the specified category
     */
    public ArrayList<String> getAvailableListingKeys(String category) {
        ArrayList<String> availableListings = new ArrayList<>();
        listings.forEach(
                (key, listing) -> {
                    if (listing.getIsAvailable() && listing.getCategoryName().equals(category)) {
                        availableListings.add(key);
                    }
                }
            }
        );
        return availableListings;
    }

    /**
     * Sorts keys based on a search word
     * @param sortBy search word
     * @return ArrayList of sorted keys, best match at index 0.
     */
    public ArrayList<String> getSortedKeys (String sortBy) {
        return ListingSorter.sortBySearchWord(sortBy, listings);
    }


    /**
     * Creates a listing from supplied parameters, adds it to the HashMap of listings, and returns the newly created listing.
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
        String[] formData = {prodName,prodDesc,String.valueOf(price),prodCat.getCategoryName(),""};
        return createListing(formData,userId);
    }

    /**
     * Creates a listing from an array with desired data, adds the created listing to the HashMap of listings, and returns the newly created listing.
     * @param formData - An Array of listing data, all in strings.
     * @return listing - The newly created listing
     */
    public Listing createListing(String[] formData, int userId) {
        //Parse data in different method?
        String prodName = formData[0];
        String prodDesc = formData[1];
        int price = Integer.parseInt(formData[2]);
        Category prodCat = getCategory(formData[3]);

        //Hardcoded values for now
        LocalDateTime startDate = LocalDateTime.of(2021,9,10,9,0);
        LocalDateTime endDate = LocalDateTime.of(2021,9,11,10,30);

        String listingId = generateListingId(userId);
        String fileName = formData[4];

        Listing listing = new Listing(listingId,prodName,prodCat,prodDesc,userId,price,startDate,endDate,fileName);

        listings.put(listingId,listing);
        linker.linkListing(listingId);

        return listing;
    }

    /**
     * Generates an alpha-numeric id used for ListingID and as a Key in the HashMap of listings
     * @param userId The userId of the creator of the listing. Used as the first part of the ListingID.
     * @return An id in form of a string
     */
    private String generateListingId(int userId) {
        String id;
            RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
            id = generator.generate(12);
        return userId+"-"+id;
    }

    /**
     * Removes the specified listing form the HashMap
     * @param listing The listing object to be removed
     */
    public void removeListing(Listing listing) {
        removeListing(listing.getListingId());
    }

    /**
     * Removes a listing via its Key/ListingID from the HashMap and also removes it from the users list of Keys
     * @param listingId The Key/ListingID of the listing to be removed.
     */
    public void removeListing(String listingId) {
        linker.removeLink(listingId);
        listings.remove(listingId);
    }
    /**
     * Gets saved Listings from database
     * @author Erik Larsson
     * @return A HashMap containing Listing objects
     *
     */
    private HashMap<String, Listing> getSavedListings() {
        HashMap<String, Listing> listingstmp = new HashMap<>();
        JSONReader reader = new JSONReader();
        List<Listing> savedListings = reader.read(Listing[].class, "listings");
        savedListings.forEach(l ->
        {
            if (l == null) {
                System.out.println("null object in json file");
            } else {
                listingstmp.put(l.getListingId(),l);
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
        ArrayList<Listing> toJson = new ArrayList<Listing>();
        for (Listing l : listings.values()) {
            toJson.add(l);
        }
        writer.write(toJson, "listings");
    }
}
