package edu.tda367.Model.Listing;

import edu.tda367.Model.JSON.JSONReader;
import edu.tda367.Model.JSON.JSONWriter;
import edu.tda367.Model.ListingLinker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

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
     * Getter for Listings
     * @return An ArrayList af all current listings
     */
    public HashMap<String, Listing> getListings() {
        return listings;
    }

    public ArrayList<Listing> getListingsAsList() {
        return new ArrayList<>(listings.values());
    }

    public Listing getListingFromKey(String key) {
        return listings.get(key);
    }

    /**
     * Getter for Listings where ListingState is Avaliable
     * @return An ArrayList af all current Avaliable listings
     */
    public ArrayList<String> getAvailableListingKeys() {
        ArrayList<String> availableListings = new ArrayList<>();
        listings.forEach(
            (key, listing) -> {
                if (listing.getListingState().equals(ListingState.AVAILABLE)) {
                    availableListings.add(key);
                }
            }
        );
        return availableListings;
    }

    public ArrayList<Integer> getMyListingIds(int userId) {
        ArrayList<Integer> ids = new ArrayList<>();
        //for (Listing listing : listings) {

        //}
        return ids;
    }


    public void sortListings (String sortBy) {
        System.out.println("handler started");
        System.out.println("Befor sorted:" + listings.get(0).getProduct().getProdName());
        ListingSorter.sortBySearchWord(sortBy, getListingsAsList());
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
        String[] formData = {prodName,prodDesc,String.valueOf(price),prodCat.getCategoryName(),""};
        return createListingFromForm(formData,userId);
    }

    /**
     * Creates a listing from an array with desired data, adds the created listing to the ArrayList of listings, and returns the newly created listing.
     * @param formData - An Array of listing data, all in strings.
     * @return listing - The newly created listing
     */
    public Listing createListingFromForm(String[] formData, int userId) {
        //Parse data in different method?
        String prodName = formData[0];
        String prodDesc = formData[1];
        int price = Integer.parseInt(formData[2]);
        Category prodCat = getCategory(formData[3]);

        //Hardcoded values for now
        LocalDateTime startDate = LocalDateTime.of(2021,9,10,9,0);
        LocalDateTime endDate = LocalDateTime.of(2021,9,11,10,30);

        String key = createKey(generateListingId(),userId);
        String fileName = formData[4];

        Listing listing = new Listing(key,prodName,prodCat,prodDesc,userId,price,startDate,endDate,fileName);

        listings.put(key,listing);
        linker.linkListing(key);

        return listing;
    }

    private String generateListingId() {
        String id;
            RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
            id = generator.generate(12);
        return id;
    }

    private String createKey(String listingId, int userId) {
        return userId+"-"+listingId;
    }

    private String extractKey(Listing listing) {
        return listing.getListingId() + listing.getUserId();
    }

    /**
     * Removes the specified listing form the ArrayList and returns it
     * @param listing
     * @return The removed listing
     */
    public Listing removeListing(Listing listing) {//TODO Maybe not necessary to return removed listing, breaks CQS.
       //TODO Also remove from relevant users list of ids,
        linker.removeLink(extractKey(listing));
        listings.remove(listing.getListingId());
        return listing;
    }

    public void removeListing(String listingId) {//TODO Maybe not necessary to return removed listing, breaks CQS.
        //TODO Also remove from relevant users list of ids,
        linker.removeLink(listingId);
        listings.remove(listingId);
    }
    /**
     * Gets saved Listings from database
     * @author Erik Larsson
     * @return An ArrayList containing Listing objects
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
