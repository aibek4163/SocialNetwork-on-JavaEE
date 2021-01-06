package aralasu.kz.db;

public class Friend_Requests {
    private Long id;
    private User user_id;
    private User request_sender_id;
    private String sent_time;

    public Friend_Requests(Long id, User user_id, User request_sender_id, String sent_time) {
        this.id = id;
        this.user_id = user_id;
        this.request_sender_id = request_sender_id;
        this.sent_time = sent_time;
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

    public User getRequest_sender_id() {
        return request_sender_id;
    }

    public void setRequest_sender_id(User request_sender_id) {
        this.request_sender_id = request_sender_id;
    }

    public String getSent_time() {
        return sent_time;
    }

    public void setSent_time(String sent_time) {
        this.sent_time = sent_time;
    }
}
