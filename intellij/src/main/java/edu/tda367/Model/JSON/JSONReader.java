
package edu.tda367.Model.JSON;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JSONReader is used to fetch data from a json file and converting it to a list of the specified object
 * @author Erik Larsson
 */
public class JSONReader {
    /**
     * Parses a specified json file, maps objects to specified class and returns a list of objects
     * @param clazz The class of the objects
     * @param name The name of the json file
     * @return An ArrayList containing objects of specified class
     */
    public <T> List<T> read(Class<T[]> clazz, String name) {
        //try (FileReader reader = new FileReader("src/main/resources/edu/tda367/JSONFiles/" + name + ".json")) {
            try (Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/edu/tda367/JSONFiles/" + name + ".json"), StandardCharsets.UTF_8)) {
            T[] arr = new Gson().fromJson(reader, clazz);
            if (arr == null || arr.length == 0) {
                System.out.println("Empty file for " + name + ", returning empty list");
                return new ArrayList<>();
            }
            return Arrays.asList(arr);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + name + ", returning empty list");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.out.println("Malformed Json syntax in " + name + ", returning empty list");
            System.out.println("Database most likely got deleted now :(");
            return new ArrayList<>();
        }
        System.out.println("No list returned");
        return new ArrayList<>();
    }
}