package ua.artcode.englishfun.html_server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.englishfun.Run;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.exception.HttpServerException;
import ua.artcode.englishfun.exception.InvalidLoginException;
import ua.artcode.englishfun.exception.InvalidTokenException;
import ua.artcode.englishfun.exception.RegisterException;
import ua.artcode.englishfun.model.users.User;
import ua.artcode.englishfun.utils.ServerUtils;

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
               File file = new File(ContextCreator.class.getResource("/static/html/Index.html").getFile());
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

    public static void regContext(HttpServer httpServer, Controller controller){
        httpServer.createContext("/register", httpExchange -> {
            String response = "";
            User user = ServerUtils.getObject(httpExchange, User.class);

            try {
                response = controller.register(user.getEmail(), user.getPass(), user.getEnglishLvl(), user.getLanguageCategories()).getMessage();
            } catch (RegisterException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            ServerUtils.sendResponse(httpExchange, response);
            controller.save();

        });
    }
    public static void loginContext(HttpServer httpServer, Controller controller){
        httpServer.createContext("/login", httpExchange -> {
            ResponseLoginModel response = new ResponseLoginModel();
            response.errorMsg = "Error! Incorrect  password";
            LoginModel loginModel = ServerUtils.getObject(httpExchange, LoginModel.class);

            try {
                Run.userToken = controller.logIn(loginModel.email, loginModel.pass);

                User user = controller.getUserDB().getUserbyToken(Run.userToken);

                if (Run.userToken.length() > 1){
                    response.setUser(user);
                    response.setErrorMsg(null);
                    response.setUserToken(Run.userToken);
                }
            } catch (InvalidLoginException | InvalidTokenException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                response.setErrorMsg(e.getMessage());
            }
            ServerUtils.sendResponse(httpExchange, new Gson().toJson(response));
            controller.save();

        });
    }




    private static class LoginModel {
        private String email;
        private String pass;
    }

    private static class ResponseLoginModel {
        private User user;
        private String successMsg;
        private String errorMsg;
        private String userToken;

        public ResponseLoginModel() {
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setSuccessMsg(String successMsg) {
            this.successMsg = successMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }
    }
}
