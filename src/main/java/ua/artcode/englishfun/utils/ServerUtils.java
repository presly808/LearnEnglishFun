package ua.artcode.englishfun.utils;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by diversaint on 07.07.17.
 */
public class ServerUtils {

    public static final int SUCCES_STATUS_CODE = 200;

    // todo close streams properly, use try with resources
    public static void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        int length = response.length();
        byte[] bytes = response.getBytes();
        httpExchange.sendResponseHeaders(SUCCES_STATUS_CODE, bytes.length);
        try(OutputStream os = httpExchange.getResponseBody()) {
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static <T> T getObject(HttpExchange httpExchange, Class<T> objectClass) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            InputStream is = httpExchange.getRequestBody();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        // todo Do we need to create Gson each time
        return new Gson().fromJson(result.toString("UTF-8"), objectClass);
    }
}
