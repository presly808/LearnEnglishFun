package ua.artcode.englishfun.html_server;

import com.sun.net.httpserver.HttpServer;
import ua.artcode.englishfun.utils.ServerUtils;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.exception.HttpServerException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.users.User;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by diversaint on 07.07.17.
 */
public class ContextCreator {


    public static void MainPage(HttpServer httpServer){
        httpServer.createContext("/", httpExchange -> {
           String response = "<h1>Page not found)</h1>";

           try {
               File file = new File(ContextCreator.class.getResource("/static/html/Main.html").getFile());
               if (!file.exists()) throw new HttpServerException("Not found main page");

               String result = String.join("\n",Files.readAllLines(Paths.get(file.getPath())));
               ServerUtils.sendResponse(httpExchange, result);
//               response = new String(Files.readAllBytes(Paths.get(file.getPath())));
           } catch (HttpServerException e) {
               e.printStackTrace();
               ServerUtils.sendResponse(httpExchange, e.getMessage());
           }

        });
    }


    public static void StaticFiles(HttpServer httpServer){
        httpServer.createContext("/static", httpExchange -> {
            String response = "<h1>Page not found)</h1>";

            URI requestURI = httpExchange.getRequestURI();
            System.out.println(requestURI);

            String classpathPublicFile = requestURI.toString();
            System.out.println(classpathPublicFile);


            try {
                File file = new File(ContextCreator.class.getResource(classpathPublicFile).getFile());
                if (!file.exists()) throw new HttpServerException("Not found main page");

                String result = String.join("\n",Files.readAllLines(Paths.get(file.getPath())));

                ServerUtils.sendResponse(httpExchange, result);
//               response = new String(Files.readAllBytes(Paths.get(file.getPath())));
            } catch (HttpServerException e) {
                e.printStackTrace();
                ServerUtils.sendResponse(httpExchange, e.getMessage());
            }

        });
    }

    public static void registerForm(HttpServer httpServer, Controller controller){
        httpServer.createContext("/register", httpExchange -> {
            String response = "";
            User user = ServerUtils.getObject(httpExchange, User.class);

            try {
                response = controller.register(user.getEmail(), user.getPass()).getMessage();
            } catch (RegisterException e) {
                e.printStackTrace();
            }
            ServerUtils.sendResponse(httpExchange, response);
            controller.save();

        });
    }


}
