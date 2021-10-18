package edu.tda367.Controllers;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageHandler {
    private static ImageHandler instance;
    public static ImageHandler getInstance(){
        if(instance == null)
            instance = new ImageHandler();
        return instance;
    }

    private final Map<String, Image> images = new HashMap<>();

    private Image loadImage(String imageName)
    {
        System.out.println(imageName);
        String imagesPath = "src/main/resources/edu/tda367/images/" + imageName;
        return new Image(imagesPath);
    }

    public Image getImage(String name) {
        Image image = images.get(name);

        if(image == null) {
            image = loadImage(name);
            images.put(name, image);
        }
        return image;
    }

 }
