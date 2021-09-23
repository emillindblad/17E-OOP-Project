
package edu.tda367.JSON;

import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONReader {
    public <T> List<T> read(Class<T[]> clazz, String name) {
        try (FileReader reader = new FileReader("src/main/resources/edu/tda367/JSONFiles/" + name + ".json")) {
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
        }
        System.out.println("No list returned");
        return new ArrayList<>();
    }
}