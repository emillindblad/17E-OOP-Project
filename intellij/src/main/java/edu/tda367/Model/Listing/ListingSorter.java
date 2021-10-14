package edu.tda367.Model.Listing;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListingSorter {

    /**
     * Sort a list of Listings based on the "searchWord", doesnt
     * @param searchWord Word to sort by
     * @param toSort List ofListings to sort
     */
    public static void sortBySearchWord(String searchWord, List<Listing> toSort) {

        HashMap<Double,Listing> map1= matchingToSearch(searchWord, toSort);
        List<Double> sortedKeys = sortKeys(map1);
        System.out.println(sortedKeys.toString());

        //adds the sorted objects back to the list
        toSort.clear();
        for (double k : sortedKeys) {
            toSort.add(map1.get(k));
        }

    }

    static private HashMap<Double, Listing> matchingToSearch (String searchWord, List<Listing> toSort) {
        System.out.println("sorting");
        LevenshteinDistance levDist = new LevenshteinDistance();
        String sequenceToTest = "";
        HashMap<Double, Listing> map = new HashMap<Double, Listing>();
        double key;

        for (int i = 0; i < toSort.size(); i++) {
            sequenceToTest = getSearchableString(toSort.get(i));//add different strings together to compare
            key = levDist.apply(searchWord, sequenceToTest);
            System.out.println(key);
            key+= ((double) i/10000); //if two objects are of equal similiarities we need some way of separating them
            map.put(key,toSort.get(i));
        }
        return map;
    }
    static private String getSearchableString (Listing makeToString) {
        return makeToString.getProduct().getProdName() + " " + makeToString.getProduct().getCategoryName() + " " + makeToString.getProduct().getDescription();
    }

    static private List<Double> sortKeys (HashMap<Double, Listing> sort) {
        List<Double> sortedKeys =  new ArrayList<>();
        for(double d : sort.keySet()){
            sortedKeys.add(d);
        }
        Collections.sort(sortedKeys);
        return sortedKeys;
    }
}
