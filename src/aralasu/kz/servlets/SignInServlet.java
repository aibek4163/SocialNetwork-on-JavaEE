package aralasu.kz.servlets;

import aralasu.kz.db.DBManager;
import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/sign_in")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String checkbox = request.getParameter("is_check");
        User user = DBManager.getUserByEmail(email);
        String redirect = "/login?email_error";
        if(user!=null){
            redirect = "/login?password_error";
            if(user.getPassword().equals(password)){
                request.getSession().setAttribute("current_user",user);
                redirect = "/feed";
            }
        }
        if(checkbox!=null){
            Cookie email_cookie = new Cookie("email_cookie",email);
            Cookie password_cookie = new Cookie("password_cookie",password);
            email_cookie.setMaxAge(3600*24*7);
            password_cookie.setMaxAge(3600*24*7);
            response.addCookie(email_cookie);
            response.addCookie(password_cookie);
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
