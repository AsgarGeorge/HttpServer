package httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerThread.class);
    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
   try {
       while(serverSocket.isBound() && !serverSocket.isClosed()) {

           Socket client = serverSocket.accept();

           LOGGER.info("connection accepted: {}", client.getInetAddress());
           InputStream inputStream = client.getInputStream();
           OutputStream outputStream = client.getOutputStream();

           String html = "<html><head><title> Java Http Server</title></head><body><h1> this is simple webserver</h1> </body></html>";

           String response = "HTTP/1.1 500 OK\n" + // status code
                   "ContentType: text/html\n" +
                   "Content-Length:" + html.getBytes().length + "\n" + // header
                   "\n" +             // blank line
                   html; //"\n\r"; //+      // content for response
           // "\n\r";          // blank line
           outputStream.write(response.getBytes());


           inputStream.close();
           outputStream.close();
           client.close();
       }
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

