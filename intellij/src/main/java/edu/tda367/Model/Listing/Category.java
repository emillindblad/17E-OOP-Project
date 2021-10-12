package edu.tda367.Model.Listing;

/**
 * Category is a class which represents a category
 * @author Emil Lindblad
 */
public class Category {
    private String categoryName;

    /**
     * Constructor to create a Category
     * @param categoryName - The desired name for a category
     */
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


    /**
     * Getter for the name of a category
     * @return A string with the name of a category.
     */
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
