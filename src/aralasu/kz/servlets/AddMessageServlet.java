package aralasu.kz.servlets;

import aralasu.kz.db.Chat;
import aralasu.kz.db.DBManager;
import aralasu.kz.db.Message;
import aralasu.kz.db.User;
import com.mysql.cj.xdevapi.DbDoc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add_message")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            Long receiver_id = Long.parseLong(request.getParameter("receiver_id"));
            String send_btn = request.getParameter("send_btn");
            Chat chat = DBManager.getChatExisted(receiver_id, user.getId());
            String message_text = request.getParameter("message_text");
            //Message message = null;
            if(send_btn!=null){
                Long chat_id = Long.parseLong(request.getParameter("chat_id"));
                //Long user_id = Long.parseLong(request.getParameter("user_id"));
                User user1 = DBManager.getUserById(receiver_id);
                Chat chat1 = DBManager.getChatById(chat_id);
                Message message = new Message(null,chat1,user1,user,message_text,false,null);
                message.setChat_id(chat1);
                chat1.setLatest_message_text(message_text);
                DBManager.updateLatestText(chat1);
                DBManager.addMessage(message);
                response.sendRedirect("/chat_details?chat_id="+chat_id.toString());
            }
            else if(chat!=null){
                User receiver_user = DBManager.getUserById(receiver_id);
                Message message = new Message(null,chat,receiver_user,user,message_text,false,null);
                message.setChat_id(chat);
                chat.setLatest_message_text(message_text);
                DBManager.updateLatestText(chat);
                DBManager.addMessage(message);
                response.sendRedirect("/chat_details");
            }else{
                User opponent_user = DBManager.getUserById(receiver_id);
                Chat new_chat1 = new Chat(user,opponent_user,null,message_text,null);
                DBManager.addChat(new_chat1);
                Message message = new Message(DBManager.getChatExisted(receiver_id, user.getId()),opponent_user,user,message_text,false,null);
                DBManager.addMessage(message);
                response.sendRedirect("/message");
            }
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
