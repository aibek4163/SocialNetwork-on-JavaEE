<%@ page import="java.util.ArrayList" %>
<%@ page import="aralasu.kz.db.Chat" %>
<%@ page import="aralasu.kz.db.Message" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 15.10.2020
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Chats</title>
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
                    <form action="/message" method="get">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="full_text" placeholder="Search messages" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" name="search_btn" id="button-addon2"><i class="fas fa-search"></i> Search</button>
                            </div>
                        </div>
                    </form>
                    <%
                        ArrayList<Chat> chats = (ArrayList<Chat>)request.getAttribute("chats");
                        ArrayList<Message> messages = (ArrayList<Message>)request.getAttribute("new_messages");
                        ArrayList<Message> founded_messages = (ArrayList<Message>)request.getAttribute("founded_messages");
                        if(chats!=null){
                            for(Chat c:chats){
                    %>
                    <form action="/chat_details" method="get">
                        <input type="hidden" name="chat_id" value="<%=c.getId()%>">
                        <div class="card mb-3 mt-3" style="max-width: 100%;">
                            <div class="row no-gutters">
                                <div class="col-md-4 pt-3 pl-5 pb-3">
                                    <a href="/chat_details?chat_id=<%=c.getId()%>"><img src="<%=c.getOpponent_user_id().getPicture_url()%>" class="rounded-circle" alt="..." width="100" height="100"></a>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <%
                                                boolean is_new_message = false;
                                                for(Message m:messages){
                                                    if(c.getId().equals(m.getChat_id().getId())){
                                                        is_new_message = true;
                                            %>
                                            <h5 class="card-title font-weight-bold italic"><a href="/chat_details?chat_id=<%=c.getId()%>">***<%=c.getOpponent_user_id().getFull_name()%>***</a></h5>
                                            <%
                                                        break;
                                                    }
                                                }if(!is_new_message){
                                            %>
                                            <h5 class="card-title"><a href="/chat_details?chat_id=<%=c.getId()%>"><%=c.getOpponent_user_id().getFull_name()%></a></h5>
                                            <%
                                                }
                                            %>
                                            <p class="card-text"><small class="text-muted"><%=c.getLatest_message_time()%></small></p>
                                        </div>
                                        <p class="card-text mt-3"><small class="text-muted"><%=c.getLatest_message_text()%></small></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <%
                            }
                        }if(founded_messages!=null){
                            for (Message m:founded_messages){
                    %>
                    <h4>Your messages: </h4>
                    <form action="/chat_details" method="get">
                        <input type="hidden" name="chat_id" value="<%=m.getId()%>">
                        <div class="card mb-3 mt-3" style="max-width: 100%;">
                            <div class="row no-gutters">
                                <div class="col-md-12">
                                    <div class="card-body ">
                                        <div class="d-flex justify-content-between">
                                            <h5 class="card-title"><a href="/chat_details?chat_id=<%=m.getChat_id().getId()%>"><%=m.getMessage_text()%></a></h5>
                                            <p class="card-text"><small class="text-muted"><%=m.getChat_id().getCreated_date()%></small></p>
                                        </div>
                                        <p class="card-text mt-3"><small class="text-muted">Sender: <%=m.getSender_id().getFull_name()%></small></p>
                                        <p class="card-text mt-3"><small class="text-muted">Receiver: <%=m.getUser_id().getFull_name()%> on <%=m.getSent_date()%></small></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <%
                            }
                        }
                    %>


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
