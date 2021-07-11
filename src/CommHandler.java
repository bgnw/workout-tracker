import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class CommHandler {

    private static int lastUpdateID = 0;

    public static ArrayList<UserMessage> checkForUpdates() {

        try {
            HttpResponse<String> rawResponse = APIHandler.GET_Request("getUpdates?offset=" + lastUpdateID);

            if (rawResponse.statusCode() > 399) {
                ErrorReporter.logError(
                        "WARNING",
                        "HTTP status code " + rawResponse.statusCode() + " encountered when checking for updates.",
                        rawResponse.body()
                );
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
                        String.valueOf(senderInfo.getInt("id")),
                        senderInfo.getString("first_name"),
                        senderInfo.getString("username"),
                        String.valueOf(chatInfo.getInt("id")),
                        msgWrapper.getString("text"),
                        String.valueOf(msgWrapper.getInt("date"))
                ));

                System.out.println("@" + senderInfo.get("username") + ": " + msgWrapper.get("text"));

                lastUpdateID++;
            }

            return messages;
        } catch (Exception e) {
            ErrorReporter.logError(
                    "WARNING",
                    "Failed to fetch new updates.",
                    e.getMessage()
            );
            return null;
        }
    }

    public static void sendMessage(String chat_id, String text) throws IOException, InterruptedException {
        APIHandler.GET_Request("sendMessage?chat_id=" + chat_id + "&text=" + APIHandler.encodeString(text));
    }

}
