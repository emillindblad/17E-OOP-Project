package edu.tda367.Model;

import edu.tda367.Model.UserPackage.UserHandler;
import java.util.ArrayList;

public class ListingLinker {
    //private ListingHandler listingHandler;
    private UserHandler userHander;


    public ListingLinker() {
        //this.listingHandler = ListingHandler.getInstance();
        this.userHander = UserHandler.getInstance();
    }

    public void linkListing(String key) {
        userHander.addListingId(key);
    }

    public void removeLink(String key) {
        userHander.removeListingId(key);
    }

    public ArrayList<String> getMyListings(int userId) {
        ArrayList<String> myListings = new ArrayList<String>();
        //HashMap<String,Listing> listings = listingHandler.getListings();
        //listings.forEach(
            //(key, listing) -> {
                //if (key.contains(String.valueOf(userId))) {
                    //myListings.add(key);
                //}
            //}
        //);
        return myListings;
    }
}
