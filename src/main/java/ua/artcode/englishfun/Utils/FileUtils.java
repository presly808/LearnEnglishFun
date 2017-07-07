package ua.artcode.englishfun.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.artcode.englishfun.DAO.UserDAO;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.contoller.MainController;
import ua.artcode.englishfun.model.Dictionary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by diversaint on 27.06.17.
 */
public class FileUtils {
    private static final String pathToUserDB = "./LearnEnglishFun/src/main/resources/database/userDB.txt";
    private static final String pathToDict = "./LearnEnglishFun/src/main/resources/database/dict.txt";


/*    public static void writeToJson(String filePath, Object object) throws IOException {
        String objectJson = new Gson().toJson(object);
        Files.write(Paths.get(filePath), objectJson.getBytes(), StandardOpenOption.CREATE);
    }*/
    public static boolean writeToJson(String filePath, Object object){
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static <T> T getFromJson(String filePath, Class<T> tclass) {
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, tclass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
