package aralasu.kz.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String email_field = "";
        String password_field = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("email_cookie")) {
                    email_field = c.getValue();
                }
                if (c.getName().equals("password_cookie")) {
                    password_field = c.getValue();
                }
            }
        }
        request.setAttribute("email_field", email_field);
        request.setAttribute("password_field", password_field);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
