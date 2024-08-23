package httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
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
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                LOGGER.info("Client is accepted: {}", client.getInetAddress());
                ServerWorkerThread serverWorkerThread = new ServerWorkerThread(client);
                serverWorkerThread.start();


            }
        } catch (IOException e) {
            LOGGER.error("problem with setting socket",e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
