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

@WebServlet(value = "/details_post")
public class DetailsPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user!=null){
            Long id = Long.parseLong(request.getParameter("id"));
            Post post = DBManager.getPost(id);
            request.setAttribute("post",post);
            request.getRequestDispatcher("/details_post.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }

    }
}
