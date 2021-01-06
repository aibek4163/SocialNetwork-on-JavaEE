package aralasu.kz.servlets;

import aralasu.kz.db.Chat;
import aralasu.kz.db.DBManager;
import aralasu.kz.db.Message;
import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/message")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            if(request.getParameter("search_btn")!=null){
                String full_text = request.getParameter("full_text");
                ArrayList<Message> founded_messages = DBManager.findMessages(full_text.toLowerCase());
                request.setAttribute("founded_messages",founded_messages);
            }
            ArrayList<Chat> chats = DBManager.getOwnChats(user.getId());
            ArrayList<Message> new_messages = DBManager.getUnreadMessageOfUser(user);
            for(Message m:new_messages){
                DBManager.read_messages(m);
            }
            request.setAttribute("new_messages",new_messages);
            request.setAttribute("chats",chats);
            request.getRequestDispatcher("/chats_page.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
