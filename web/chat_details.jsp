<%@ page import="aralasu.kz.db.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="aralasu.kz.db.Chat" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 16.10.2020
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Chat details</title>
</head>
    <body>
    <%@include file="include/navbar.jsp"%>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <img src="<%=user.getPicture_url()%>" height="200" class="w-100">
                <div class="card mt-5" style="width: 100%;">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><%=user.getFull_name()%>, <%=user.getAge() + " years"%></li>
                        <li class="list-group-item"><a href="/profile"><i class="fas fa-id-card"></i>My Profile</a></li>
                        <li class="list-group-item"><a href="#"><i class="fas fa-cogs"></i>Settings</a></li>
                        <li class="list-group-item"><a href="/logout" style="color: #862F3B"><i class="fas fa-sign-out-alt"></i>Log out</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-6">
                <form action="/add_message" method="post">


                <%
                    ArrayList<Message> messages = (ArrayList<Message>)request.getAttribute("messages");
                    if(messages!=null){
                        for(Message m:messages){
                %>
                <div class="card mb-3 mt-3" style="max-width: 100%;">
                    <div class="row no-gutters">
                        <div class="col-md-4 pt-3 pl-5 pb-3">
                            <a href="/user_page?user_id=<%=m.getUser_id()%>"><img src="<%=m.getSender_id().getPicture_url()%>" class="rounded-circle" alt="..." width="100" height="100"></a>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <h5 class="card-title"><a href="/user_page?user_id=<%=m.getSender_id().getId()%>"><%=m.getSender_id().getFull_name()%></a></h5>
                                    <p class="card-text"><small class="text-muted"><%=m.getSent_date()%></small></p>
                                </div>
                                <p class="card-text mt-3"><small class="text-muted"><%=m.getMessage_text()%></small></p>
                            </div>
                        </div>
                    </div>
                </div>
                    <input type="hidden" name="chat_id" value="<%=m.getChat_id().getId()%>">
                    <%
                        if(m.getUser_id().getId().equals(user.getId())){
                    %>
                    <input type="hidden" name="receiver_id" value="<%=m.getSender_id().getId()%>">
                    <%
                        }else {
                    %>
                    <input type="hidden" name="receiver_id" value="<%=m.getUser_id().getId()%>">
                    <%
                        }
                    %>
                <%
                        }
                    }
                %>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" name="message_text" placeholder="Enter messages" aria-label="Recipient's username" aria-describedby="button-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" name="send_btn" id="button-addon2"><i class="fab fa-telegram-plane"></i> Send</button>
                        </div>
                    </div>
                </form>

            </div>

            <div class="col-3">
                <div class="card bg-light mb-3" style="max-width: 18rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                        <h5 class="card-title">Light card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
                <div class="card bg-light mb-3" style="max-width: 18rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                        <h5 class="card-title">Light card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
