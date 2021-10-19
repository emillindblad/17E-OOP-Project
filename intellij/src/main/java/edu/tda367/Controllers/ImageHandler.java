package edu.tda367.Controllers;

import javafx.scene.image.Image;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling Images loads and also saves images in cache for faster loading times
 */
public class ImageHandler {
    private static ImageHandler instance;

    /**
     * Singleton pattern for ImageHandler instance
     * @return ImageHandler instance
     */
    public static ImageHandler getInstance(){
        if(instance == null)
            instance = new ImageHandler();
        return instance;
    }

    // Map used for saving images when programming is running to avoid loading the images multiple times during the application is running
    private final Map<String, Image> images = new HashMap<>();

    /**
     * Loads the image specified from the pre-defined folder
     * @param imageName name of the requested image to load
     * @return Loaded image from the imageName
     */
    private Image loadImage(String imageName)
    {
        System.out.println(imageName);
        String imagesPath = "src/main/resources/edu/tda367/images/" + imageName;
        return new Image(new File(imagesPath).toURI().toString());
    }

    /**
     * Checks if the image has been loaded before it has not been it loads the image
     * @param name name of the requested image to fetch
     * @return request image from name
     */
    public Image getImage(String name) {
        Image image = images.get(name);

        if(image == null) {
            image = loadImage(name);
            images.put(name, image);
        }
        return image;
    }

 }
