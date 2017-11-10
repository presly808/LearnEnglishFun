package ua.artcode.englishfun;

import com.sun.net.httpserver.HttpServer;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.html_server.ContextCreator;

import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * Created by serhii on 24.06.17.
 */
public class Run {
    public static String userToken;
    public static void main(String[] args) throws Exception {
        //TranslateGoogleApiImpl translateGoogleApi = new TranslateGoogleApiImpl();
        //Word word  = translateGoogleApi.translate("hi", Language.en, Language.uk);
        Controller controller = new Controller();
        controller.load();
        //controller.getUserDB().getUsers().get(0).addToWordsToStudy(word);
        controller.save();

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContextCreator.MainPage(server);
        ContextCreator.StaticFiles(server);
        ContextCreator.regContext(server, controller);
        ContextCreator.loginContext(server, controller);
        ContextCreator.moveWordToLearnedContext(server, controller);
        ContextCreator.addToWordsToStudyContext(server, controller);
        ContextCreator.apiImgConext(server, controller);
        server.setExecutor(null);
        server.start();

        // todo add logging, but currently we can not see when server has started
    }
}
