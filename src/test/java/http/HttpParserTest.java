package http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
    HttpParser httpParser;

    @BeforeAll
    public void beforeClass(){
    httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
        httpParser.parseHttpRequest(generateValidTestCase());
    }
    private InputStream generateValidTestCase(){
        String rawDate = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Connection: keep-alive\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.6 Safari/605.1.15\r\n" +
                "Accept-Language: en-US,en;q=0.9\r\n" +
                "Sec-Fetch-Dest: document\r\n"+
                "Accept-Encoding: gzip, deflate" + "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawDate.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
}