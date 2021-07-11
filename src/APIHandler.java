import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIHandler {
    private static final String BASE_URL = "https://api.telegram.org/bot1838421396:AAERCHAJXBOMFx1Y_cD5isZpzUYWDFFlH_0/";
    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public static HttpResponse<String> GET_Request(String cmd) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + cmd))
                .build();
        HttpResponse<String> rsp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        return rsp;
    }



}