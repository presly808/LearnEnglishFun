package ua.artcode.englishfun.Utils;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import sun.misc.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * Created by diversaint on 07.07.17.
 */
public class ServerUtils {
    public static final int SUCCES_STATUS_CODE = 200;
    public static void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(SUCCES_STATUS_CODE, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }

    public static <T> T getObject(HttpExchange httpExchange, Class<T> objectClass) throws IOException {
        InputStream is = httpExchange.getRequestBody();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return new Gson().fromJson(result.toString("UTF-8"), objectClass);
    }
}
