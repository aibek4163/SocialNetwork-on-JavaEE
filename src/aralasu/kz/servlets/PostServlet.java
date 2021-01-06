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

@WebServlet(value = "/post")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            //Long id = Long.parseLong(request.getParameter("user_id"));
            ArrayList<Post> post_by_id = DBManager.getPostsById(user.getId());
            ArrayList<Post> posts = DBManager.getPosts();
            //request.setAttribute("posts", posts);
            request.setAttribute("post_user", post_by_id);
            request.getRequestDispatcher("/posts.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }

    }
}
