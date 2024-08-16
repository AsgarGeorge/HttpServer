package httpserver;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Driver Class
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("server starting");
        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
//        System.out.println("port: "+ configuration.getPort());
//        System.out.println("webroot:"+ configuration.getWebroot());
        try {
            ServerSocket serverSocket = new ServerSocket(configuration.getPort());
            Socket client = serverSocket.accept();
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();

            String html= "<html><head><title> Java Http Server</title></head><body><h1> this is simple webserver</h1> </body></html>";
            //String html= "<!DOCTYPE html> <html> <body> <h1 align='center'> <font color='Red'> This is index.html </font></h1> </body></html>";


            String response = "HTTP/1.1 500 OK\n"+ // status code
                    "ContentType: text/html\n"+
                    "Content-Length:"+ html.getBytes().length+"\n"+ // header
                    "\n"+             // blank line
                    html; //"\n\r"; //+      // content for response
                   // "\n\r";          // blank line
            outputStream.write(response.getBytes());



            inputStream.close();
            outputStream.close();
            client.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
