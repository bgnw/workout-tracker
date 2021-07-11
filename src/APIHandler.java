import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class APIHandler {
    private static final String BASE_URL = "https://api.telegram.org/bot"+Secrets.API_KEY+"/";
    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public static HttpResponse<String> GET_Request(String cmd) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + cmd))
                .build();
        HttpResponse<String> rsp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        return rsp;
    }

    public static String encodeString(String text) {
        try {
            return URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            ErrorReporter.logError(
                    "ERROR",
                    "Illegal characters in string led to encoding failure.",
                    "Original text: " + text
            );
            return null;
        }
    }


}