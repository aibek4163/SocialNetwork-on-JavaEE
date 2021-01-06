package aralasu.kz.db;

public class Message {
    private Long id;
    private Chat chat_id;
    private User user_id;
    private User sender_id;
    private String message_text;
    private Boolean read_by_receiver;
    private String sent_date;

    public Message(Long id, Chat chat_id, User user_id, User sender_id, String message_text, Boolean read_by_receiver, String sent_date) {
        this.id = id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.sender_id = sender_id;
        this.message_text = message_text;
        this.read_by_receiver = read_by_receiver;
        this.sent_date = sent_date;
    }

    public Message(Chat chat_id, User user_id, User sender_id, String message_text, Boolean read_by_receiver, String sent_date) {
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.sender_id = sender_id;
        this.message_text = message_text;
        this.read_by_receiver = read_by_receiver;
        this.sent_date = sent_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat_id() {
        return chat_id;
    }

    public void setChat_id(Chat chat_id) {
        this.chat_id = chat_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public User getSender_id() {
        return sender_id;
    }

    public void setSender_id(User sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Boolean getRead_by_receiver() {
        return read_by_receiver;
    }

    public void setRead_by_receiver(Boolean read_by_receiver) {
        this.read_by_receiver = read_by_receiver;
    }

    public String getSent_date() {
        return sent_date;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }
}
