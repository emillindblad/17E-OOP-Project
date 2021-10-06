package edu.tda367.Controllers;

import edu.tda367.Model.InputChecker;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.View.SceneHandler;

import java.util.ArrayList;

public class ListingController implements Controller {
    private final ListingHandler listingHandler;
    private final SceneHandler sceneHandler;

    public ListingController(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
        this.listingHandler = ListingHandler.getInstance();
        //listingHandler.createListing("P.R.I.T. Grill", new Category("test"), "testing stuff", 4, 160, LocalDateTime.of(2021,9,10,9,0), LocalDateTime.of(2021,9,10,9,1));
    }

    public void switchToBrowse() {
        sceneHandler.switchTo("browse");
        sceneHandler.centerOnScreen();
    }

    public String createListing(String[] formData) {
        boolean foo = validateData(formData);
         if (foo) { //Return true if valid input.
             listingHandler.createListingFromString(formData);
             switchToBrowse();
             return "Success";
         }
         else {
             System.out.println("Form input failed validation!");
             return "Fail";
         }
    }

    public ArrayList<String> getCategoryNames() {
        return listingHandler.getCategoryNames();
    }

    public boolean validateData(String[] formData) { //TODO Better way to do this?
        if (!InputChecker.missingInput(formData[0])) {
            return false;
        }
        if (!InputChecker.missingInput(formData[1])) {
            return false;
        }
        if (!InputChecker.checkForNumber(formData[2])) {
            return false;
        }
        if (!InputChecker.missingInput(formData[3])) {
            return false;
        }
        return true;// Should return true if valid input
    }

    public int getPrice() {
        return listingHandler.getListings().get(0).getPrice();
    }
}
