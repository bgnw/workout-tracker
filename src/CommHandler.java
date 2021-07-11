import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommHandler {

    private static int lastUpdateID = 0;

    public static ArrayList<UserMessage> checkForUpdates() throws IOException, InterruptedException {

        HttpResponse<String> rawResponse = APIHandler.GET_Request("getUpdates?offset=" + lastUpdateID);

        if (rawResponse.statusCode() > 399) {
            System.out.println(
                    "*** WARNING: Error " + rawResponse.statusCode() + " encountered at " +
                            ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT) +
                            "\n JSON Dump: " + rawResponse.body());
        }

        JSONObject response = new JSONObject(rawResponse.body());

        JSONArray updates = response.getJSONArray("result");

        ArrayList<UserMessage> messages = new ArrayList<>();

        for (int i = 0; i < updates.length(); i++) {
            lastUpdateID = (int) updates.getJSONObject(i).get("update_id");
            JSONObject msgWrapper = updates.getJSONObject(i).getJSONObject("message");
            JSONObject chatInfo = msgWrapper.getJSONObject("chat");
            JSONObject senderInfo = msgWrapper.getJSONObject("from");

            messages.add(new UserMessage(
                    senderInfo.getString("id"),
                    senderInfo.getString("first_name"),
                    senderInfo.getString("username"),
                    chatInfo.getString("id"),
                    msgWrapper.getString("text"),
                    msgWrapper.getString("date")
            ));

            System.out.println("@" + senderInfo.get("username") + ": " + msgWrapper.get("text"));

            lastUpdateID++;
        }
    }

    public static void sendMessage(String chat_id, String text) throws IOException, InterruptedException {
        APIHandler.GET_Request("sendMessage?chat_id="+chat_id+"text="+text);
    }

}
