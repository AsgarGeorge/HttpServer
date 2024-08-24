package http;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HttpParserTest extends TestCase {
    private static HttpParser httpParser;
    @BeforeAll
    public static void beforeClass(){
        httpParser = new HttpParser();
    }

    @Test
    public void testParseHttpRequest() {
        httpParser.parseHttpRequest(generateValidTestCase());
    }
    private InputStream generateValidTestCase(){
        String rawData ="GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Connection: keep-alive\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.6 Safari/605.1.15\r\n" +
                "Accept-Language: en-US,en;q=0.9\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip," + "\r\n";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
}