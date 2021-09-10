package edu.tda367;

public class Product {
    private final String prodName;
    private final Category prodCategory;
    private final String prodDesc;

    public Product(String prodName, Category prodCategory, String prodDesc) {
        this.prodName = prodName;
        this.prodCategory = prodCategory;
        this.prodDesc = prodDesc;
    }
}
