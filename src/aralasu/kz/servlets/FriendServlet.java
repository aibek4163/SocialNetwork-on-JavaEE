package aralasu.kz.servlets;

import aralasu.kz.db.DBManager;
import aralasu.kz.db.Friend;
import aralasu.kz.db.Friend_Requests;
import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/friends")
public class FriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String full_name = request.getParameter("full_name");
        if(request.getParameter("search_btn")!=null){
            ArrayList<User> users = DBManager.findUsers(full_name);
            ArrayList<Friend_Requests> friend_requests = DBManager.getAllRequests();
            for(Friend_Requests f:friend_requests) {
                System.out.println(f.getId()+" "+f+" "+friend_requests);
            }
            ArrayList<Friend> friends = DBManager.getAllFriends();
            request.setAttribute("friend_requests", friend_requests);
            request.setAttribute("friends", friends);
            request.setAttribute("founded_user",users);
            request.getRequestDispatcher("/friends_page.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        if(user!=null) {
            String full_name = request.getParameter("full_name");
            if (request.getParameter("search_btn") != null) {
                ArrayList<User> users = DBManager.findUsers(full_name);
                request.setAttribute("founded_user", users);
            }
            ArrayList<Friend_Requests> current_requests = DBManager.getCurrentRequests(user.getId());
            ArrayList<Friend_Requests> friend_requests = DBManager.getAllRequests();
            ArrayList<Friend> friends = DBManager.getAllFriends();
            request.setAttribute("current_requests", current_requests);
            request.setAttribute("friend_requests", friend_requests);
            request.setAttribute("friends", friends);
            request.getRequestDispatcher("/friends_page.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
