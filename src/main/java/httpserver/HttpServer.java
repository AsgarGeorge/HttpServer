package httpserver;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;

// Driver Class
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("server starting");
        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port: "+ configuration.getPort());
        System.out.println("webroot:"+ configuration.getWebroot());
    }
}
