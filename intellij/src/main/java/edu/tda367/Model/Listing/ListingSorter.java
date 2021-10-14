package edu.tda367.Model.Listing;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Static class to sort a List<Listing> based on a searchWord.
 * Algorithm is based on the LevenshteinDistance algorithm from Apache.commons library.
 *
 * @author Sebastian Kvaldén
 */
public class ListingSorter {

    /**
     * Sort a list of Listings based on the "searchWord", doesnt
     * @param searchWord Word to sort by
     * @param toSort List ofListings to sort
     */
    public static void sortBySearchWord(String searchWord, List<Listing> toSort) {
        System.out.println("sorting by:"+ searchWord);
        HashMap<Double,Listing> map1= matchingToSearch(searchWord, toSort);
        List<Double> sortedKeys = sortKeys(map1);
        System.out.println(sortedKeys.toString());

        //adds the sorted objects back to the list
        toSort.clear();
        for (double k : sortedKeys) {
            System.out.println(map1.get(k).getProduct().getProdName());
            toSort.add(map1.get(k));
        }
        System.out.println("Firts in list:" + toSort.get(0).getProduct().getProdName());

    }

    static private HashMap<Double, Listing> matchingToSearch (String searchWord, List<Listing> toSort) {
        ArrayList <String> sequencesToTest = new ArrayList<>();
        HashMap<Double, Listing> map = new HashMap<Double, Listing>();
        double key;

        for (int i = 0; i < toSort.size(); i++) {
            sequencesToTest = getSearchableStrings(toSort.get(i));//add different strings together to compare
            key = findBestMatch(sequencesToTest, searchWord);
            System.out.println(key);
            key+= ((double) i/10000); //if two objects are of equal similiarities we need some way of separating them
            map.put(key,toSort.get(i));
        }
        return map;
    }
    //method to find the best match with regards to name, category and description
    static private double findBestMatch (ArrayList <String> words, String searchWord) {
        LevenshteinDistance levDist = new LevenshteinDistance();
        int temp;
        int lowest = levDist.apply(searchWord,words.get(0));
        for(int i = 1; i< words.size(); i++) {
            temp = levDist.apply(searchWord,words.get(i));
            if (temp < lowest)
                lowest = temp;
        }
        return lowest;
    }

    static private ArrayList<String> getSearchableStrings (Listing makeToString) {
        ArrayList words = new ArrayList();
        words.add(makeToString.getProduct().getProdName());
        words.add(makeToString.getProduct().getCategoryName());
        words.add( makeToString.getProduct().getDescription());
        return words;
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
