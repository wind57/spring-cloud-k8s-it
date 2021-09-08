package zero.x.spring.k8s;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestApp {

    private static final HttpClient CLIENT;

    static {
        CLIENT = HttpClient.newHttpClient();
    }

    @Test
    public void test1() throws IOException, InterruptedException {

        String result = CLIENT.send(
                HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:30008/yaml")).build(),
                HttpResponse.BodyHandlers.ofString()
        ).body();

        Assertions.assertEquals(result, "value1");


        result = CLIENT.send(
                HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:30008/props")).build(),
                HttpResponse.BodyHandlers.ofString()
        ).body();

        Assertions.assertEquals(result, "value2");

        result = CLIENT.send(
                HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:30008/non-spring")).build(),
                HttpResponse.BodyHandlers.ofString()
        ).body();

        Assertions.assertEquals(result, "value3");

    }

}
