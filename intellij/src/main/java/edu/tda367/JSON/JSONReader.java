
package edu.tda367.JSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONReader { //TODO: Implement writer first

    public <clazz> ArrayList read(String className, String classSimpleName) {

        try (FileReader reader = new FileReader("src/main/resources/edu/tda367/JSONFiles/" + classSimpleName + "s" + ".json")) {
            Class clazz;
            clazz = Class.forName(className);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<clazz>>(){}.getType();
            ArrayList<clazz> list = gson.fromJson(reader, listType);
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("No list returned");
        return null;
    }

    public <T> ArrayList<T> fromJSonList(String json, Class<T> type) {
        Gson gson = new Gson();
        Type collectionType = TypeToken.getParameterized(List.class, type).getType();
        return gson.fromJson(json, collectionType);
    }
}