public class UserMessage {
    protected String user_id;
    protected String first_name;
    protected String username;
    protected String chat_id;
    protected String messageBody;
    protected String timestamp;

    public UserMessage(String user_id, String first_name, String username, String chat_id, String messageBody, String timestamp) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.username = username;
        this.chat_id = chat_id;
        this.messageBody = messageBody;
        this.timestamp = timestamp;
    }
}
