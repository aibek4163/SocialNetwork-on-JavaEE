<%@ page import="aralasu.kz.db.User" %>
<%@ page import="aralasu.kz.db.DBManager" %><%
    User user = (User)request.getSession().getAttribute("current_user");
    int unread_message = 0;
    boolean isOnline = false;
    if(user!=null){
        isOnline = true;
        unread_message = DBManager.getUnreadMessageOfUser(user).size();
    }
%>