package edu.tda367;

import javafx.scene.image.Image;

public class Product {
    private final String prodName;
    private final Category prodCategory;
    private final String prodDesc;
    private final Image prodImg;

    public Product(String prodName, Category prodCategory, String prodDesc, Image prodImg) {
        this.prodName = prodName;
        this.prodCategory = prodCategory;
        this.prodDesc = prodDesc;
        this.prodImg = prodImg;
    }
}
