package edu.tda367.View;

import javafx.scene.Scene;


/**
 * Interface that all scenes implemented it includes methods that all scenes should have
 */
public interface hyroScene {

    /**
     * Gets the JavaFX scene from Class
     * @return
     */
    Scene getHyroScene();

    /**
     * Method for updating scene when for example a scene switches.
     * The application need some way to load new information so that it displays correctly in the GUI these methods are called in the update method.
     */
    void update();
}