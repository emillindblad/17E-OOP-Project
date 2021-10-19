package edu.tda367.Model.Listing;

/**
 * Product
 * @author Emil Lindblad
 */
public class Product {
    private final String prodName;
    private final Category prodCategory;
    private String prodDesc;
    //private final Image prodImg;
    //TODO do the bild

    public Product(String prodName, Category prodCategory, String prodDesc) {
        this.prodName = prodName;
        this.prodCategory = prodCategory;
        this.prodDesc = prodDesc;
        //this.prodImg = prodImg;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodName='" + prodName + '\'' +
                ", prodCategory=" + prodCategory +
                ", prodDesc='" + prodDesc + '\'' +
                '}';
    }

    public Category getProdCategory() {
        return prodCategory;
    }

    public String getProdName() {
        return this.prodName;
    }

    public String getCategoryName() {
        return this.prodCategory.getCategoryName();
    }

    public String getDescription(){ return this.prodDesc;}

    public void setDescription(String desc) {
        prodDesc = desc;
    }
}
