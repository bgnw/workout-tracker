import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class APIHandler {
    private static final String BASE_URL = "https://api.telegram.org/bot1838421396:AAERCHAJXBOMFx1Y_cD5isZpzUYWDFFlH_0/";
    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static int lastUpdateID = 0;

    public static void GetRequest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL+"getUpdates?offset="+lastUpdateID))
                .build();
        HttpResponse<String> rawResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (rawResponse.statusCode() > 399) {
            System.out.println("*** WARNING: Error "+rawResponse.statusCode()+" encountered at " +
                    ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT ) +
                    "\n JSON Dump: " + rawResponse.body());
        }

        JSONObject response = new JSONObject(rawResponse.body());

        JSONArray updates = response.getJSONArray("result");
        for (int i = 0; i < updates.length(); i++) {
            lastUpdateID = (int) updates.getJSONObject(i).get("update_id");
            JSONObject msg = updates.getJSONObject(i).getJSONObject("message");

            System.out.println("@" + msg.getJSONObject("from").get("username") + ": " + msg.get("text"));

            lastUpdateID++;
        }
    }

}