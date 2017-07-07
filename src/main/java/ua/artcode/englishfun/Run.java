package ua.artcode.englishfun;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.englishfun.DAO.UserDAO;
import ua.artcode.englishfun.Utils.DictUtils;
import ua.artcode.englishfun.Utils.FileUtils;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.html_server.ContextCreator;
import ua.artcode.englishfun.model.Dictionary;
import ua.artcode.englishfun.model.category.Language;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Paths;


/**
 * Created by serhii on 24.06.17.
 */
public class Run {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        controller.load();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        ContextCreator.MainPage(server);
        server.setExecutor(null);
        server.start();

    }
}
