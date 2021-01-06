package aralasu.kz.servlets;

import aralasu.kz.db.DBManager;
import aralasu.kz.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit_user")
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("current_user");
        if(current_user!=null){
            String btn_update_profile = request.getParameter("btn_update_profile");
            String btn_update_picture = request.getParameter("btn_update_picture");
            String btn_update_password = request.getParameter("btn_update_password");

            if(btn_update_profile!=null){
                Long id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getUserById(id);
                String email = request.getParameter("email");
                String full_name = request.getParameter("full_name");
                String date = request.getParameter("date");
                String redirect = "/profile?same_email";
                if(user!=null){
//                if(!email.equals(DBManager.getUserByEmail(email).getEmail())){
//                    user.setEmail(email);
//                    user.setFull_name(full_name);
//                    user.setBirth_date(date);
//                    DBManager.updateUserProfile(user);
//                    request.getSession().setAttribute("current_user",user);
//                    redirect="/profile?updated_success";
//                }
                    user.setEmail(email);
                    user.setFull_name(full_name);
                    user.setBirth_date(date);
                    DBManager.updateUserProfile(user);
                    request.getSession().setAttribute("current_user",user);
                    redirect="/profile?updated_success";
                }
                response.sendRedirect(redirect);
            }
            else if(btn_update_picture!=null){
                Long id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getUserById(id);
                String picture_url = request.getParameter("picture_url");
                if(user!=null){
                    user.setPicture_url(picture_url);
                    DBManager.updateUserPicture(user);
                    request.getSession().setAttribute("current_user",user);
                    response.sendRedirect("/profile");
                }
            }
            else if(btn_update_password!=null){
                Long id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getUserById(id);
                String old_password = request.getParameter("old_password");
                String new_password = request.getParameter("new_password");
                String re_new_password = request.getParameter("re_new_password");
                String redirect = "";
                if(user != null){
                    if(!old_password.equals(user.getPassword())){
                        redirect = "/profile?old_password";
                    }
                    else if(old_password.equals(user.getPassword()) && new_password.equals(re_new_password)){
                        user.setPassword(new_password);
                        DBManager.updateUserPassword(user);
                        request.getSession().setAttribute("current_user",user);
                        redirect="/profile?success";
                    }else {
                        redirect="/profile?new_password";
                    }
                }
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
