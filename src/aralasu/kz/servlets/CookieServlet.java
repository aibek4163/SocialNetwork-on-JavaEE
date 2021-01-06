package aralasu.kz.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/set_cookie")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkbox = request.getParameter("is_check");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(checkbox!=null){
            Cookie email_cookie = new Cookie("email_cookie",email);
            Cookie password_cookie = new Cookie("password_cookie",password);
            email_cookie.setMaxAge(3600*24*7);
            password_cookie.setMaxAge(3600*24*7);
            response.addCookie(email_cookie);
            response.addCookie(password_cookie);
            response.sendRedirect("/sign_in");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
