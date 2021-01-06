package aralasu.kz.db;

public class Friend {
    private Long id;
    private User user_id;
    private User friend_id;
    private String  added_time;

    public Friend(Long id, User user_id, User friend_id, String added_time) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.added_time = added_time;
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

    public User getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(User friend_id) {
        this.friend_id = friend_id;
    }

    public String getAdded_time() {
        return added_time;
    }

    public void setAdded_time(String added_time) {
        this.added_time = added_time;
    }
}
