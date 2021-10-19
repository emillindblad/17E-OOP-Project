package edu.tda367.Model;

import edu.tda367.Model.Listing.Listing;
import javafx.scene.image.Image;

public interface RentingItemEntry {
    /**
     * Getter for entry's product name
     * @return prodouct name
     */
    String getProductName();

    /**
     * Getter for entry's listing price
     * @return listing price
     */
    int getPrice();

    /**
     * Getter for entry's category name
     * @return category name
     */
    String getCategoryName();

    /**
     * Getter for entry's status text
     * @return status text
     */
    String getStatusText();

    /**
     * Getter for entry's button text
     * @return button text
     */
    String getButtonText();

    /**
     * Advances state of renting entry.
     * Will make sure state of booking and listing corresponds
     */
    void advanceState();

    /**
     * Getter for if entry is clickable to enter detail/settings view
     * @return true if clickable
     */
    boolean getClickable();

    /**
     * Getter for the listing connected to entry
     * @return entry's listing
     */
    Listing getListing();

    /**
     * Getter for entry's image name
     * @return image name
     */
    String getImageName();
}
