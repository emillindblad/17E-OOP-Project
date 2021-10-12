package edu.tda367.Model.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * JSONWriter is used to write data to a json file
 * @author Erik Larsson
 */
public class JSONWriter {

    /**
     *
     * @param list The list of objects to be written
     * @param name The name of the json file
     */
    public void write(List list, String name) {
        try {

            // create Gson instance with pretty-print
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/edu/tda367/JSONFiles/" + name + ".json"));

            // convert list of objects to JSON file
            gson.toJson(list, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
