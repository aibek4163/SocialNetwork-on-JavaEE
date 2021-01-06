package aralasu.kz.servlets;

import aralasu.kz.db.DBManager;
import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add_user")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String full_name = request.getParameter("full_name");
        String date = request.getParameter("date");
        String default_picture = "https://otvet.imgsmail.ru/download/689367f58323fc96e83911b5bc5f5902_i-15.jpg";
        User user = null,new_user = null;
        String redirect = "/register?password_error";
        if(password.equals(re_password)){
            redirect = "/register?email_error";
            user = DBManager.getUserByEmail(email);
            if(user==null){
                new_user = new User(null,email,password,full_name,date,default_picture);
                if(DBManager.addUser(new_user)) {
                    redirect = "/register?success";
                }
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
