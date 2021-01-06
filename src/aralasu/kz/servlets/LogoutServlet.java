package aralasu.kz.servlets;

import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("current_user")!=null) {
            request.getSession().removeAttribute("current_user");
            request.getSession().invalidate();
            //request.getSession().setMaxInactiveInterval(0);
            response.sendRedirect("/login");
        }
    }
}
