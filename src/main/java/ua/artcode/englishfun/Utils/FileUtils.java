package ua.artcode.englishfun.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.artcode.englishfun.model.Dictionary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by diversaint on 27.06.17.
 */
public class FileUtils {
    public static boolean writeToJson(String filePath, Object object){
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();
        try {
            gson.toJson(object, new FileWriter(filePath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static <T> T getFromJson(String filePath, Class<T> tclass) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(filePath), tclass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
