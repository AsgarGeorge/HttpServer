package httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

            LOGGER.info("connection processing finished.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
