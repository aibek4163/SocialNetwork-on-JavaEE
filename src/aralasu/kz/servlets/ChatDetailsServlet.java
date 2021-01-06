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

@WebServlet(value = "/chat_details")
public class ChatDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            Long chat_id = Long.parseLong(request.getParameter("chat_id"));
            ArrayList<Message> message = DBManager.getMessageByChatId(chat_id);
            request.setAttribute("messages",message);
            request.getRequestDispatcher("/chat_details.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
