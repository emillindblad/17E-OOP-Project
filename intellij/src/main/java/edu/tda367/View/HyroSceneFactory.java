package edu.tda367.View;

import edu.tda367.Controllers.AccountViewController;
import edu.tda367.View.scenes.Home;
import edu.tda367.View.scenes.LogIn;
import edu.tda367.View.scenes.AccountView;
import edu.tda367.View.scenes.Secondary;
import edu.tda367.View.scenes.*;

import java.io.IOException;

public final class HyroSceneFactory {
    public static final hyroScene homeScene(SceneHandler handler) throws IOException {
        return new Home(handler);
    }

    public static final hyroScene secondaryScene(SceneHandler handler) throws IOException {
        return new Secondary(handler);
    }

    public static final hyroScene loginScene(SceneHandler handler) throws IOException {
        return new LogIn(handler);
    }

    public static final hyroScene accountViewScene(SceneHandler handler, AccountViewController controller) throws IOException {
        return new AccountView(handler, controller);
    }
    public static final hyroScene browseScene(SceneHandler director) throws IOException {
        return new BrowseListings(director);
    }

    public static final hyroScene createListingScene(SceneHandler director) throws IOException {
        return new CreateListingScene(director);
    }

    public static final hyroScene rentingPage(SceneHandler director) throws IOException {
        return new RentingPage(director);
    }
}