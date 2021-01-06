package aralasu.kz.servlets;

import aralasu.kz.db.DBManager;
import aralasu.kz.db.Post;
import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/feed")
public class FeedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            ArrayList<Post> posts = DBManager.getFriendsPosts(user.getId());
            ArrayList<Post> user_post = DBManager.getPostsById(user.getId());
            ArrayList<Post> all_post = new ArrayList<>(posts);
            all_post.addAll(user_post);
            request.setAttribute("posts",all_post);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
