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
           ServerWorkerThread serverWorkerThread = new ServerWorkerThread(client);
           serverWorkerThread.run();


       }
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

