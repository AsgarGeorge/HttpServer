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
        InputStream inputStream =null;
        OutputStream outputStream = null;

        try {
             inputStream = client.getInputStream();
             outputStream = client.getOutputStream();
             int _byte;
             while((_byte = inputStream.read()) >= 0){
                 System.out.print((char)_byte);
             }



             String html = "<html><head><title> Java Http Server</title></head><body><h1> this is simple webserver</h1> </body></html>";
             String response = "HTTP/1.1 500 OK\r\n" +
                    "ContentType: text/html\r\n" +
                    "Content-Length:" + html.getBytes().length + "\r\n" +
                    "\r\n" +
                    html;
            outputStream.write(response.getBytes());
            //sleep(5000);

            LOGGER.info("connection processing finished.");
        }catch (IOException e) {
            LOGGER.error("problem with communication ",e);
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            if(client != null){

                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
