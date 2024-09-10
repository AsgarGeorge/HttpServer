package httpserver;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;
import httpserver.core.ServerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// Driver Class
public class HttpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {
        System.out.println("server starting");
        LOGGER.info("server starting..");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("port: "+ configuration.getPort());
        LOGGER.info("webroot:"+ configuration.getWebroot());


        try {
            ServerThread thread = new ServerThread(configuration.getPort(),configuration.getWebroot());
            thread.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
