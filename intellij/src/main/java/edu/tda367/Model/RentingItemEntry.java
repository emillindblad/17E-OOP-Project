package edu.tda367.Model;

import edu.tda367.Model.Listing.Listing;
import javafx.scene.image.Image;

public interface RentingItemEntry {
    String getProductName();
    int getPrice();
    String getCategoryName();
    Image getImage();
    String getStatusText();
    String getButtonText();
    void advanceState();
    boolean getClickable();
    Listing getListing();
}
