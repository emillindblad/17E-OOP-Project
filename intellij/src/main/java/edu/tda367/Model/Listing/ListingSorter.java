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
     * Sort a list of Listings based on the "searchWord", doesnt
     * @param searchWord Word to sort by
     * @param toSort List ofListings to sort
     */
    public static ArrayList<String> sortBySearchWord(String searchWord, HashMap<String,Listing> toSort) {
        matchRate = 15;
        HashMap<Double,String> map1= matchingListToSearch(searchWord, toSort);
        ArrayList<String> sortedKeys = sortAndFilterKeys(map1);
        return sortedKeys;
    }

    //returns mathcing value (double, and listing) can return key instead?
    static private HashMap<Double, String> matchingListToSearch (String searchWord, HashMap<String, Listing> toSort) {
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
