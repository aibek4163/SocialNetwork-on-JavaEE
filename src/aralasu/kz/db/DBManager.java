package aralasu.kz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    public DBManager(){

    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(Long id){
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


    public static User getUserByEmail(String email){
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static ArrayList<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts ORDER BY post_date DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static Post getPost(Long id){
        Post post = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                post = new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return post;
    }

    public static boolean addUser(User user){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users(id,email,password,full_name,birth_date,picture_url) VALUES(NULL,?,?,?,?,?)");
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFull_name());
            statement.setString(4,user.getBirth_date());
            statement.setString(5,user.getPicture_url());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean addPost(Post post){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO posts(id,author_id,title,short_content,content,post_date) VALUES(NULL,?,?,?,?,?)");
            statement.setLong(1,post.getUser_id().getId());
            statement.setString(2,post.getTitle());
            statement.setString(3,post.getShort_content());
            statement.setString(4,post.getContent());
            statement.setString(5,post.getPost_date());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }


    public static boolean updateUserPicture(User user){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET picture_url = ? WHERE id = ?");
            statement.setString(1,user.getPicture_url());
            statement.setLong(2,user.getId());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean updateUserPassword(User user){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            statement.setString(1,user.getPassword());
            statement.setLong(2,user.getId());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean updateUserProfile(User user){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?,full_name = ?,birth_date = ? WHERE id = ?");
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getFull_name());
            statement.setString(3,user.getBirth_date());
            statement.setLong(4,user.getId());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }


    public static boolean updateUser(User user){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?,password = ?,full_name = ?,birth_date = ?,picture_url = ? WHERE id = ?");
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFull_name());
            statement.setString(4,user.getBirth_date());
            statement.setLong(5,user.getId());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean updatePost(Post post){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE posts SET title = ?,short_content = ?,content = ? WHERE id = ?");
            statement.setString(1,post.getTitle());
            statement.setString(2,post.getShort_content());
            statement.setString(3,post.getContent());
            statement.setLong(4,post.getId());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean deleteUser(Long id){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1,id);
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean deletePost(Long id){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM posts WHERE id = ?");
            statement.setLong(1,id);
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static ArrayList<Post> getPostsById(Long id){
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE author_id = ? ORDER BY post_date DESC");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<User> findUsers(String full_name){
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE full_name LIKE ?");
            statement.setString(1,"%"+full_name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static boolean friendsRequest(Long user_id,Long request_sender_id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO friends_requests(id,user_id,request_sender_id) VALUES(NULL,?,?)");
            statement.setLong(1,user_id);
            statement.setLong(2,request_sender_id);
            rows = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Friend_Requests> getAllRequests(){
        ArrayList<Friend_Requests> requests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friends_requests");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                requests.add(new Friend_Requests(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("request_sender_id")),
                        resultSet.getString("sent_time")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return requests;
    }

    public static ArrayList<Friend_Requests> getCurrentRequests(Long id){
        ArrayList<Friend_Requests> requests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friends_requests WHERE user_id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                requests.add(new Friend_Requests(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("request_sender_id")),
                        resultSet.getString("sent_time")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return requests;
    }

    public static ArrayList<Friend> getAllFriends(){
        ArrayList<Friend> friends = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friends");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                friends.add(new Friend(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("friend_id")),
                        resultSet.getString("added_time")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return friends;
    }

    public static boolean addFriend(Long user_id,Long friend_id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO friends(id,user_id,friend_id) VALUES(NULL,?,?)");
            statement.setLong(1,user_id);
            statement.setLong(2,friend_id);
            rows = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean rejectRequest(Long id){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM friends_requests WHERE id = ?");
            statement.setLong(1,id);
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static boolean deleteFriend(Long id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM friends WHERE id = ?");
            statement.setLong(1,id);
            rows = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Post> getFriendsPosts(Long id){
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM posts p INNER JOIN friends f ON p.author_id=f.friend_id  WHERE f.user_id=? ORDER BY post_date DESC");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

    public static boolean deleteFriend(Long user_id,Long friend_id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM friends WHERE user_id = ? AND friend_id = ?");
            statement.setLong(1,user_id);
            statement.setLong(2,friend_id);
            rows = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean deleteRequest(Long user_id,Long friend_id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM friends_requests WHERE user_id = ? AND request_sender_id = ?");
            statement.setLong(1,user_id);
            statement.setLong(2,friend_id);
            rows = statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Chat> getAllChats(){
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM chats");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                chats.add(new Chat(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("opponent_user_id")),
                        resultSet.getString("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getString("latest_message_time")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return chats;
    }

    public static Chat getChatById(Long id){
        Chat c = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM chats WHERE id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                c = new Chat(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("opponent_user_id")),
                        resultSet.getString("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getString("latest_message_time")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    public static ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChatById(resultSet.getLong("chat_id")),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getString("sent_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public static Message getMessageById(Long id){
        Message message = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE id=?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                message = new Message(
                        resultSet.getLong("id"),
                        DBManager.getChatById(resultSet.getLong("chat_id")),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getString("sent_date")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    public static boolean addMessage(Message message){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO messages (id,chat_id,user_id,sender_id,message_text,read_by_receiver)" +
                    "VALUES (NULL,?,?,?,?,?) ");
            statement.setLong(1,message.getChat_id().getId());
            statement.setLong(2,message.getUser_id().getId());
            statement.setLong(3,message.getSender_id().getId());
            statement.setString(4,message.getMessage_text());
            statement.setBoolean(5,message.getRead_by_receiver());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean addChat(Chat chat){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO chats (id,user_id,opponent_user_id,latest_message_text)" +
                    "VALUES (NULL,?,?,?)");
            statement.setLong(1,chat.getUser_id().getId());
            statement.setLong(2,chat.getOpponent_user_id().getId());
            statement.setString(3,chat.getLatest_message_text());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static Chat getChatExisted(Long user_id,Long opponent_user_id){
        Chat chat = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM chats WHERE (user_id = ? AND opponent_user_id = ?) OR (opponent_user_id=? AND user_id=?)");
            statement.setLong(1,user_id);
            statement.setLong(2,opponent_user_id);
            statement.setLong(3,user_id);
            statement.setLong(4,opponent_user_id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                chat = new Chat(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("opponent_user_id")),
                        resultSet.getString("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getString("latest_message_time")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return chat;
    }

    public static void read_messages(Message message){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE messages SET read_by_receiver = ? WHERE id = ?");
            statement.setBoolean(1,true);
            statement.setLong(2,message.getId());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Message> findMessages(String full_text){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE message_text LIKE ?");
            statement.setString(1,"%"+full_text+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChatById(resultSet.getLong("chat_id")),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getString("sent_date")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public static boolean updateLatestText(Chat chat){
        int row = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE chats SET latest_message_text = ?,latest_message_time=NOW() WHERE id = ?");
            statement.setString(1,chat.getLatest_message_text());
            statement.setLong(2,chat.getId());
            row = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return row > 0;
    }

    public static ArrayList<Chat> getOwnChats(Long user_id){
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM chats " +
                    "WHERE user_id = ? OR opponent_user_id = ? ");
            statement.setLong(1,user_id);
            statement.setLong(2,user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(!user_id.equals(resultSet.getLong("opponent_user_id"))){
                    chats.add(new Chat(
                            resultSet.getLong("id"),
                            DBManager.getUserById(resultSet.getLong("user_id")),
                            DBManager.getUserById(resultSet.getLong("opponent_user_id")),
                            resultSet.getString("created_date"),
                            resultSet.getString("latest_message_text"),
                            resultSet.getString("latest_message_time")
                    ));
                }else {
                    chats.add(new Chat(
                            resultSet.getLong("id"),
                            DBManager.getUserById(resultSet.getLong("opponent_user_id")),
                            DBManager.getUserById(resultSet.getLong("user_id")),
                            resultSet.getString("created_date"),
                            resultSet.getString("latest_message_text"),
                            resultSet.getString("latest_message_time")
                    ));
                }
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return chats;
    }

    public static ArrayList<Message> getMessageByChatId(Long id){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE chat_id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChatById(resultSet.getLong("chat_id")),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getString("sent_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public static ArrayList<Message> getUnreadMessageOfUser(User user){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE user_id = ? AND read_by_receiver = ?");
            statement.setLong(1,user.getId());
            statement.setBoolean(2,false);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChatById(resultSet.getLong("chat_id")),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getString("sent_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public static ArrayList<Message> getMessagesByUser(Long id){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE user_id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChatById(resultSet.getLong("chat_id")),
                        DBManager.getUserById(resultSet.getLong("user_id")),
                        DBManager.getUserById(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getString("sent_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }
}
