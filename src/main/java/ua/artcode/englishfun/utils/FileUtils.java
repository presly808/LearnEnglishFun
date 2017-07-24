package ua.artcode.englishfun.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by diversaint on 27.06.17.
 */
public class FileUtils {

    // todo get files via ClassLoader from resources
    private static final String pathToUserDB = "./LearnEnglishFun/src/main/resources/database/userDB.txt";
    private static final String pathToDict = "./LearnEnglishFun/src/main/resources/database/dict.txt";

/*    public static void writeToJson(String filePath, Object object) throws IOException {
        String objectJson = new Gson().toJson(object);
        Files.write(Paths.get(filePath), objectJson.getBytes(), StandardOpenOption.CREATE);
    }*/
    public static boolean writeToJson(String filePath, Object object){
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();

        File classPathFile = new File(FileUtils.class.getResource(filePath).getFile());

        try(FileWriter writer = new FileWriter(classPathFile)) {
            gson.toJson(object, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static <T> T getFromJson(String filePath, Class<T> tclass) {
        Gson gson = new Gson();

        InputStream classPathInputStream = FileUtils.class.getResourceAsStream(filePath);

        try(Reader reader = new InputStreamReader(classPathInputStream)) {
            return gson.fromJson(reader, tclass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
