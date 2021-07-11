import java.util.ArrayList;

public class MessageInterpreter {
    private static ArrayList<UserMessage> queue = new ArrayList<>();

    public static void updateQueue() {

        ArrayList<UserMessage> newMessages = CommHandler.checkForUpdates();
        queue.addAll(newMessages);

    }

    public static void handleMessages() {
        for (int i = 0; i < queue.size(); i++) {
            UserMessage userMsg = queue.get(i);
            String responseMsg;


            switch (userMsg.messageBody) {
                case "/start":
                    responseMsg = "Hello! This bot will track your workouts, log them, and provide statistics.";
                    break;
                default:
                    responseMsg = "Sorry, I don't understand your message – send /start to get started :)";
            }

            try {
                CommHandler.sendMessage(userMsg.chat_id, responseMsg);
            } catch (Exception e) {
                ErrorReporter.logError(
                        "WARNING",
                        "Sending message to user failed.",
                        e.getMessage()
                );
            }

            queue.remove(i);


        }
    }

}
