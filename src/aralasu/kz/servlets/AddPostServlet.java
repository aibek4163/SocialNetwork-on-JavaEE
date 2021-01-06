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

@WebServlet(value = "/add_post")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            String title = request.getParameter("post_title");
            String post_short_content = request.getParameter("post_short_content");
            String post_content = request.getParameter("post_content");
            Long id = Long.parseLong(request.getParameter("user_id"));
            String redirect = "/post?success";
            Post post = new Post(null,DBManager.getUserById(id),title,post_short_content,post_content,null);
            if(DBManager.addPost(post)){
                response.sendRedirect(redirect);
            }else {
                redirect = "/post?error";
                response.sendRedirect(redirect);
            }
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
