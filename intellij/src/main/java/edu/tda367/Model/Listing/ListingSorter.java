package edu.tda367.Model.Listing;

import org.apache.commons.text.similarity.CosineDistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListingSorter {

    public static List<Listing> sortBySearchWord(String searchWord, List<Listing> toSort) {
        List<Listing> toReturn = new ArrayList<>();

        HashMap<Double,Listing> map1= matchingToSearch(searchWord, toSort);
        List<Double> sortedKeys = sortKeys(map1);

        //adds the sorted objects back to a list
        for (double k : sortedKeys) {
            toReturn.add(map1.get(k));
        }
        return toReturn;
    }

    static private HashMap<Double, Listing> matchingToSearch (String searchWord, List<Listing> toSort) {
        CosineDistance cosDist = new CosineDistance();
        String sequenceToTest = "";
        HashMap<Double, Listing> map = new HashMap<Double, Listing>();
        double key;

        for (int i = 0; i < toSort.size(); i++) {
            sequenceToTest = getSearchableString(toSort.get(i));//add different string together to compare
            key = cosDist.apply(searchWord, sequenceToTest);
            if(key == 1.0) { //object gets lost otherwise
                map.put(key + i, toSort.get(i));
            }
            else {
                map.put(key, toSort.get(i));
            }
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
