package aralasu.kz.db;

public class Post {
    private Long id;
    private User user_id;
    private String title;
    private String short_content;
    private String content;
    private String post_date;

    public Post(Long id, User user_id, String title, String short_content, String content, String post_date) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }
}
