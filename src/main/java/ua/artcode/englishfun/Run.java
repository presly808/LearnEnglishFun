package ua.artcode.englishfun;

import com.sun.net.httpserver.HttpServer;
import ua.artcode.englishfun.contoller.Controller;
import ua.artcode.englishfun.html_server.ContextCreator;

import java.net.InetSocketAddress;


/**
 * Created by serhii on 24.06.17.
 */
public class Run {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        controller.load();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        ContextCreator.MainPage(server);
        ContextCreator.StaticFiles(server);
        ContextCreator.registerForm(server, controller);
        server.setExecutor(null);
        server.start();

        // todo add logging, but currently we can not see when server has started
    }
}
