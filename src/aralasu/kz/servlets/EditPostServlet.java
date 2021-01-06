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

@WebServlet(value = "/edit_post")
public class EditPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            if(request.getParameter("edit_btn")!=null){
                if(request.getParameter("own_post")!=null){
                    Long id = Long.parseLong(request.getParameter("post_id"));
                    String title = request.getParameter("post_title");
                    String post_short = request.getParameter("post_short_content");
                    String post_content = request.getParameter("post_content");
                    Post post = DBManager.getPost(id);
                    if(post!=null){
                        post.setTitle(title);
                        post.setShort_content(post_short);
                        post.setContent(post_content);
                        DBManager.updatePost(post);
                        response.sendRedirect("/feed");
                    }
                }else {
                    Long id = Long.parseLong(request.getParameter("post_id"));
                    String title = request.getParameter("post_title");
                    String post_short = request.getParameter("post_short_content");
                    String post_content = request.getParameter("post_content");
                    Post post = DBManager.getPost(id);
                    if (post != null) {
                        post.setTitle(title);
                        post.setShort_content(post_short);
                        post.setContent(post_content);
                        DBManager.updatePost(post);
                        response.sendRedirect("/post");
                    }
                }
            }
            else if(request.getParameter("delete_btn")!=null){
                Long id =Long.parseLong(request.getParameter("post_id"));
                DBManager.deletePost(id);
                response.sendRedirect("/post");
            }
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
