package ua.artcode.englishfun.html_server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.englishfun.Run;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.exception.*;
import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.Word;
import ua.artcode.englishfun.model.users.User;
import ua.artcode.englishfun.utils.ServerUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

    public static void addToWordsToStudyContext(HttpServer httpServer, Controller controller){
        httpServer.createContext("/moveWordToStudy", httpExchange -> {
            ResponseModel response = new ResponseModel();
            Word word = ServerUtils.getObject(httpExchange, Word.class);
            try {
                User user = controller.getUserDB().getUserbyToken(Run.userToken);
                user.addToWordsToStudy(word);
                response.setUser(user);
                //todo chek added word in common dictionary and add it to dict

            } catch (InvalidTokenException | InvalidWordException e) {
                e.printStackTrace();
                response.setErrorMsg(e.getMessage());
            }
            ServerUtils.sendResponse(httpExchange, new Gson().toJson(response));
            controller.save();
        });
    }
    public static void moveWordToLearnedContext(HttpServer httpServer, Controller controller){
        httpServer.createContext("/moveWordToLearned", httpExchange -> {
            ResponseModel response = new ResponseModel();
            Word word = ServerUtils.getObject(httpExchange, Word.class);
            try {
                User user = controller.getUserDB().getUserbyToken(Run.userToken);
                user.moveWordToLearned(word);
                response.setUser(user);

            } catch (InvalidTokenException | InvalidWordException e) {
                e.printStackTrace();
                response.setErrorMsg(e.getMessage());
            }
            ServerUtils.sendResponse(httpExchange, new Gson().toJson(response));
            controller.save();
        });
    }

    public static void apiImgConext(HttpServer httpServer, Controller controller){
        httpServer.createContext("/imgApi", httpExchange -> {
            String response = "";
            String urlApi = ServerUtils.getObject(httpExchange, String.class);
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            System.setProperty("http.agent", "Chrome");
            try {
                URL url = new URL(urlApi);
                InputStream is = url.openStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }

                response = String.valueOf(result);
                //todo add img url to word and save in db

            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerUtils.sendResponse(httpExchange, response);
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
            ResponseModel response = new ResponseModel();
            response.errorMsg = "Error! Incorrect  password";
            LoginModel loginModel = ServerUtils.getObject(httpExchange, LoginModel.class);

            try {
                Run.userToken = controller.logIn(loginModel.email, loginModel.pass);

                User user = controller.getUserDB().getUserbyToken(Run.userToken);

                if (Run.userToken.length() > 1){
                    response.setUser(user);
                    response.setErrorMsg(null);
                    response.setUserToken(Run.userToken);
                    response.setDictionary(controller.getDictionary());
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

    private static class ResponseModel {
        private User user;
        private Dictionary dictionary;
        private String successMsg;
        private String errorMsg;
        private String userToken;

        public ResponseModel() {
        }

        public void setDictionary(Dictionary dictionary) {
            this.dictionary = dictionary;
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
