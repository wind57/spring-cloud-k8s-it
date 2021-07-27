package zero.x.spring.cloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestSingleEntryInConfigMap {

    private static final HttpClient CLIENT;

    static {
        CLIENT = HttpClient.newHttpClient();
    }

    @Test
    public void test1() throws IOException, InterruptedException {
        String result = CLIENT.send(
                HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:30008/key1")).build(),
                HttpResponse.BodyHandlers.ofString()
        ).body();

        Assertions.assertEquals(result, "no-name-value1");
    }

}
