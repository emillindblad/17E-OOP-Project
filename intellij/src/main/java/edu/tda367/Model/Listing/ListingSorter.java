package edu.tda367.Model.Listing;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.*;

/**
 * Static class to sort a List<Listing> based on a searchWord.
 * Algorithm is based on the LevenshteinDistance algorithm from Apache.commons library.
 *
 * @author Sebastian Kvaldén
 */
public class ListingSorter {
    private static int matchRate; //set to 1 to search for categories

    /**
     * Sort a HashMap of Listings based on the "searchWord"
     * @param searchWord Word to sort by
     * @param toSort HashMap of Listings to sort
     * @return List of sorted keys to the map
     */
    public static ArrayList<String> sortBySearchWord(String searchWord, HashMap<String,Listing> toSort) {
        matchRate = 15;
        HashMap<Double,String> map1= matchingMapToSearch(searchWord, toSort);
        ArrayList<String> sortedKeys = sortAndFilterKeys(map1);
        return sortedKeys;
    }

    /**
     * Compares a search word to values in a map and matches them by a "double" value
     * @param searchWord Word too compare to
     * @param toSort map of Listings to compare
     * @return Map with similarity as keys, and  sorted Keys to the original list as Values
     */
    static private HashMap<Double, String> matchingMapToSearch (String searchWord, HashMap<String, Listing> toSort) {
        ArrayList <String> sequencesToTest; //array with listing name, description, category
        HashMap<Double, String> map = new HashMap<>();
        double similiarity;
        int i=0;

        for (Map.Entry<String,Listing> entry :toSort.entrySet()) {
            sequencesToTest = getSearchableStrings(entry.getValue());//add different strings together to compare
            similiarity = findBestMatch(sequencesToTest, searchWord);
            System.out.println(similiarity);
            similiarity+= ((double) i/10000); //if two objects are of equal similiarities we need some way of separating them
            map.put(similiarity, entry.getKey()); //stores the similarity of lsitings/searches with their keys
            i++;
        }
        return map;
    }

    /**
     * Makes an array of all searchable information in a Listing
     * @param makeToString Listing to form a string from
     * @return Array of searchable strings
     */
    static private ArrayList<String> getSearchableStrings (Listing makeToString) {
        ArrayList words = new ArrayList();
        words.add(makeToString.getProduct().getProdName());
        words.add(makeToString.getProduct().getCategoryName());
        words.add( makeToString.getProduct().getDescription());
        return words;
    }

    /**
     * Compares a search word and finds the most relevant String in a array.
     * @param words Array of strings to compare
     * @param searchWord String to compare them with
     * @return Similarity value for the best matching string in the Array.
     */
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

    /**
     * Filter out bad matching objects and sort the remaining keys in order. Best to worst.
     * @param sort Map<Double, String> to sort. Map should come from "MatchingMapToSearchWord"-method.
     * @return Sorted and filtered keys to original Map
     */
    static private ArrayList<String> sortAndFilterKeys (HashMap<Double, String> sort) {
        ArrayList<String> keys = new ArrayList<>();
        ArrayList <Double> compareValues = new ArrayList<>(sort.keySet());
        Collections.sort(compareValues);
        for (double d : compareValues) {
            System.out.println("matched by:" + d);
            if (d < matchRate) {
                System.out.println("Adding this key to list");
                keys.add(sort.get(d));
            }
        }
        System.out.println(keys.toString());
        return keys;
    }
}
