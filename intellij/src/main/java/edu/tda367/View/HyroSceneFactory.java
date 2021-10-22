package edu.tda367.View;

import edu.tda367.Controllers.*;
import edu.tda367.View.scenes.LogIn;
import edu.tda367.View.scenes.AccountView;
import edu.tda367.View.scenes.*;

import java.io.IOException;

public final class HyroSceneFactory {

    public static final HyroScene loginScene(LogInController controller) throws IOException {
        return new LogIn(controller);
    }

    public static final HyroScene accountViewScene(AccountViewController controller) throws IOException {
        return new AccountView(controller);
    }
    public static final HyroScene browseScene(BrowseController controller) throws IOException {
        return new BrowseListings(controller);
    }

    public static final HyroScene createListingSettingsScene(ListingViewController controller) throws IOException {
        return new ListingSettingsView(controller);
    }

    public static final HyroScene rentingPage(MyListingsController controller) throws IOException {
        return new RentingPage(controller);
    }
}