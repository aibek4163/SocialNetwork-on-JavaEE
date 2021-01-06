package aralasu.kz.db;

public class Chat {
    private Long id;
    private User user_id;
    private User opponent_user_id;
    private String created_date;
    private String latest_message_text;
    private String latest_message_time;

    public Chat(Long id, User user_id, User opponent_user_id, String created_date, String latest_message_text, String latest_message_time) {
        this.id = id;
        this.user_id = user_id;
        this.opponent_user_id = opponent_user_id;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
    }

    public Chat(User user_id, User opponent_user_id, String created_date, String latest_message_text, String latest_message_time) {
        this.user_id = user_id;
        this.opponent_user_id = opponent_user_id;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public User getOpponent_user_id() {
        return opponent_user_id;
    }

    public void setOpponent_user_id(User opponent_user_id) {
        this.opponent_user_id = opponent_user_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getLatest_message_text() {
        return latest_message_text;
    }

    public void setLatest_message_text(String latest_message_text) {
        this.latest_message_text = latest_message_text;
    }

    public String getLatest_message_time() {
        return latest_message_time;
    }

    public void setLatest_message_time(String latest_message_time) {
        this.latest_message_time = latest_message_time;
    }
}
