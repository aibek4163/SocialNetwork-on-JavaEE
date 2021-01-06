package aralasu.kz.servlets;

import aralasu.kz.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/user_page")
public class UserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("current_user");
        if(current_user!=null) {
            Long user_id = Long.parseLong(request.getParameter("user_id"));
            if(user_id!=null){
                User user = DBManager.getUserById(user_id);
                ArrayList<Post> posts = DBManager.getPostsById(user_id);
                request.setAttribute("post_user", posts);
                request.setAttribute("user", user);
            }
            ArrayList<Friend> friends = DBManager.getAllFriends();
            ArrayList<Friend_Requests> friend_requests = DBManager.getAllRequests();
            request.setAttribute("friend_requests", friend_requests);
            request.setAttribute("friends", friends);
            request.getRequestDispatcher("/users_page.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
