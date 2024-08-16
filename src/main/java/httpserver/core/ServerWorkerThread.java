package httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 * This class is for handling multiple connections at a time
 */

public class ServerWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerWorkerThread.class);
    private Socket client;

    public ServerWorkerThread(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();

            String html = "<html><head><title> Java Http Server</title></head><body><h1> this is simple webserver</h1> </body></html>";

            String response = "HTTP/1.1 500 OK\n" +
                    "ContentType: text/html\n" +
                    "Content-Length:" + html.getBytes().length + "\n" +
                    "\n" +
                    html;
            sleep(5000);
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            client.close();

            LOGGER.info("connection processing finished.");
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
