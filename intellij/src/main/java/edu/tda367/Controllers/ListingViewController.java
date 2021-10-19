package edu.tda367.Controllers;

import edu.tda367.Model.InputChecker;
import edu.tda367.Model.Listing.ListingHandler;
import edu.tda367.Model.UserPackage.UserHandler;
import edu.tda367.View.SceneHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public abstract class ListingViewController implements Controller {
    protected final ListingHandler listingHandler;
    protected final UserHandler userHandler;
    protected final SceneHandler sceneHandler;

    /**
     * Constructor
     * @param sceneHandler SceneHandler for switching scenes
     */
    public ListingViewController(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
        this.listingHandler = ListingHandler.getInstance();
        this.userHandler = UserHandler.getInstance();
        //listingHandler.createListing("P.R.I.T. Grill", new Category("test"), "testing stuff", 4, 160, LocalDateTime.of(2021,9,10,9,0), LocalDateTime.of(2021,9,10,9,1));
    }

    /**
     * Gets all category names
     * @return hardcoded list of category names
     */
    public ArrayList<String> getCategoryNames() {
        return listingHandler.getCategoryNames();
    }

    /**
     * Checks so data is correct for creating/editing listing
     * @param formData data to be checked
     * @return true if data is correct, false if not
     */
    protected boolean validateData(String[] formData) { //TODO Better way to do this?
        if (!InputChecker.anyInput(formData[0])) {
            return false;
        }
        if (!InputChecker.anyInput(formData[1])) {
            return false;
        }
        if (!InputChecker.checkForNumber(formData[2])) {
            return false;
        }
        if (!InputChecker.anyInput(formData[3])) {
            return false;
        }
        return true;// Should return true if valid input
    }

    /**
     * Method called when done button is pressed
     * Will try to either create or edit listing and give feedback on what went wrong
     * @param formData Data listing is to be created/edited with
     * @param src Image file for listing
     * @param destPath Destination of image for listing
     * @return Status message on what went wrong/"Success"
     */
    public String doneButton(String[] formData, File src, String destPath) {
        int userId = userHandler.getUserID();
        if (validateData(formData)) { //Return true if valid input.
            if(src != null) {
                try {
                    Files.copy(src.toPath(), new File(destPath + src.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("did save");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            String msg = updateListings(formData, userId);
            goBack();
            return msg;
        }
        else {
            System.out.println("Form input failed validation!");
            return "Fail";
        }
    }

    protected abstract String updateListings(String[] formData, int userID);

    /**
     * Returns to previous page
     */
    public abstract void goBack();

    /**
     * Getter for FXML file name
     * @return FXML file name of file to be controlled
     */
    public abstract String getFXMLName();

    /**
     * Called whenever scene is switched to.
     * Updates fields to contain right information.
     * @param nameField Field for product name
     * @param descField Field for product description
     * @param priceField Field for listing price
     * @param categoriesDropdown Dropdown menu for categories
     */
    public abstract void update(TextField nameField, TextField descField, TextField priceField, ComboBox<String> categoriesDropdown);
}
