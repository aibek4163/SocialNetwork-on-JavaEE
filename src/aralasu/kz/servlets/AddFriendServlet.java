package aralasu.kz.servlets;

import aralasu.kz.db.DBManager;
import aralasu.kz.db.User;
import com.mysql.cj.xdevapi.DbDoc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add_friend")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null) {
            if (request.getParameter("confirm") != null) {
                Long friend_id = Long.parseLong(request.getParameter("friend_id"));
                Long request_id = Long.parseLong(request.getParameter("confirm"));
                DBManager.addFriend(user.getId(), friend_id);
                DBManager.addFriend(friend_id, user.getId());
                DBManager.rejectRequest(request_id);
                response.sendRedirect("/friends");
            } else if (request.getParameter("reject") != null) {
                Long id = Long.parseLong(request.getParameter("reject"));
                DBManager.rejectRequest(id);
                response.sendRedirect("/friends");
            } else if (request.getParameter("delete_request") != null) {
                Long id = Long.parseLong(request.getParameter("delete_request"));
                DBManager.rejectRequest(id);
                response.sendRedirect("/friends");
            } else if (request.getParameter("add_friend") != null) {
                Long id = Long.parseLong(request.getParameter("add_friend"));
                DBManager.friendsRequest(id, user.getId());
                response.sendRedirect("/friends");
            }
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null) {
            if (request.getParameter("request_sender_id") != null) {
                Long request_sender_id = Long.parseLong(request.getParameter("request_sender_id"));
                DBManager.friendsRequest(request_sender_id, user.getId());
            } else if (request.getParameter("remove_friend") != null) {
//                Long remove_friend = Long.parseLong(request.getParameter("remove_friend"));
//                DBManager.deleteFriend(remove_friend);
//                DBManager.deleteFriend(remove_friend+1);
                Long delete_friend = Long.parseLong(request.getParameter("remove_friend"));
                DBManager.deleteFriend(user.getId(),delete_friend);
                DBManager.deleteFriend(delete_friend, user.getId());
            } else if (request.getParameter("request_sent") != null) {
                Long request_sent = Long.parseLong(request.getParameter("request_sent"));
                DBManager.rejectRequest(request_sent);
            }else if(request.getParameter("confirm")!=null){
                Long confirm_id = Long.parseLong(request.getParameter("confirm"));
                DBManager.addFriend(user.getId(), confirm_id);
                DBManager.addFriend(confirm_id, user.getId());
                DBManager.deleteRequest(user.getId(), confirm_id);
                DBManager.deleteRequest(confirm_id, user.getId());
            }
            else if(request.getParameter("reject")!=null){
                Long reject_id = Long.parseLong(request.getParameter("reject"));
                DBManager.deleteRequest(user.getId(), reject_id);
                DBManager.deleteRequest(reject_id, user.getId());
            }
            request.getRequestDispatcher("/friends_page.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
