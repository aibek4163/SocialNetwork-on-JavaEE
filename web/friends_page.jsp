<%@ page import="java.util.ArrayList" %>
<%@ page import="aralasu.kz.db.Friend" %>
<%@ page import="aralasu.kz.db.Friend_Requests" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 08.10.2020
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Friends</title>
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
                    <form action="/friends" method="get">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="full_name" placeholder="Search friends" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" name="search_btn" id="button-addon2"><i class="fas fa-search"></i> Search</button>
                            </div>
                        </div>
                    </form>
                    <%
                        ArrayList<Friend_Requests> current_requests = (ArrayList<Friend_Requests>)request.getAttribute("current_requests");
                        ArrayList<User> founded_users = (ArrayList<User>)request.getAttribute("founded_user");
                        ArrayList<Friend> friends = (ArrayList<Friend>)request.getAttribute("friends");
                        ArrayList<Friend_Requests> friend_requests = (ArrayList<Friend_Requests>)request.getAttribute("friend_requests");
                        if(founded_users!=null){
                            for(User u:founded_users){
                    %>
                    <form action="/add_friend" method="post">
                        <input type="hidden" name="user_id" value="<%=u.getId()%>">
                        <div class="card mb-3" style="max-width: 100%;">
                            <div class="row no-gutters">
                                <div class="col-md-4 pt-3 pl-5">
                                    <a href="/user_page?user_id=<%=u.getId()%>"><img src="<%=u.getPicture_url()%>" class="rounded-circle" alt="..." width="100" height="100"></a>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title"><a href="/user_page?user_id=<%=u.getId()%>"><%=u.getFull_name()%></a></h5>
                                        <p class="card-text"><small class="text-muted"><%=u.getAge()%> old</small></p>
                                        <%
                                            if(friend_requests!=null){
                                                boolean check = false;
                                                for(Friend_Requests f_r:friend_requests){
                                                    if(user.getId().equals(f_r.getRequest_sender_id().getId()) && u.getId().equals(f_r.getUser_id().getId()) ){
                                                        check = true;
                                        %>
                                        <button name="delete_request" value="<%=f_r.getId()%>"><i class="fas fa-check"></i> Request Sent</button>
                                        <%
                                           }
                                        %>

                                        <%
                                            }if(!check){
                                        %>
                                        <button name="add_friend" value="<%=u.getId()%>"><i class="fas fa-plus-circle"></i> Add to Friend</button>
                                        <%
                                                }
                                            }else {
                                        %>
                                        <button name="add_friend" value="<%=u.getId()%>"><i class="fas fa-plus-circle"></i> Add to Friend</button>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <%
                            }
                        }
                         if(friend_requests!=null){
                    %>
                    <div class="card">
                        <div class="card-header">
                            You have <%=current_requests.size()%> new requests
                        </div>
                        <%
                                for(Friend_Requests f:friend_requests){
                                    if(user.getId().equals(f.getUser_id().getId())){

                        %>
                        <form action="/add_friend" method="post">
                            <input type="hidden" name="friend_id" value="<%=f.getRequest_sender_id().getId()%>">
                            <div class="card-body">
                                <div class="row no-gutters">
                                    <div class="col-md-4 pt-3 pl-5">
                                        <a href="/user_page?user_id=<%=f.getRequest_sender_id().getId()%>"><img src="<%=f.getRequest_sender_id().getPicture_url()%>" class="rounded-circle" alt="..." width="100" height="100"></a>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <h5 class="card-title"><a href="/user_page?user_id=<%=f.getRequest_sender_id().getId()%>"><%=f.getRequest_sender_id().getFull_name()%></a></h5>
                                            <p class="card-text"><small class="text-muted"><%=f.getRequest_sender_id().getAge()%> old</small></p>
                                            <button name="confirm" value="<%=f.getId()%>"><i class="fas fa-plus-circle"></i> Confirm</button>
                                            <button name="reject" value="<%=f.getId()%>"><i class="fas fa-trash-alt"></i> Reject</button>
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
                        <%
                        }

                         if(friends!=null){
                            for(Friend f:friends){
                                if(user.getId().equals(f.getUser_id().getId())){
                    %>
                    <form action="/friends" method="get">
                        <input type="hidden" name="user_id" value="<%=f.getFriend_id().getId()%>">
                        <div class="card mb-3 mt-3" style="max-width: 100%;">
                            <div class="row no-gutters">
                                <div class="col-md-4 pt-3 pl-5">
                                    <a href="/user_page?user_id=<%=f.getFriend_id().getId()%>"><img src="<%=f.getFriend_id().getPicture_url()%>" class="rounded-circle" alt="..." width="100" height="100"></a>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title"><a href="/user_page?user_id=<%=f.getFriend_id().getId()%>"><%=f.getFriend_id().getFull_name()%></a></h5>
                                        <p class="card-text"><small class="text-muted"><%=f.getFriend_id().getAge()%> old</small></p>
                                        <button type="button" class="btn" data-toggle="modal" data-target="#send_message<%=f.getFriend_id().getId()%>" style="padding: 0;"><a href="#">
                                            <i class="fab fa-telegram-plane"></i>
                                            Send Message
                                        </a>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- Modal -->
                    <div class="modal fade" id="send_message<%=f.getFriend_id().getId()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <form action="/add_message" method="post">
                                <input type="hidden" name="receiver_id" value="<%=f.getFriend_id().getId()%>">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Send Message to <%=f.getFriend_id().getFull_name()%></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <textarea name="message_text" class="form-control"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        <button  class="btn btn-success" name="send_message">Send</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%
                                }
                                %>
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


<%--                    <form action="/friends" method="get">--%>
<%--                        <input type="hidden" name="user_id" value="<%=f.getRequest_sender_id().getId()%>">--%>
<%--                        <div class="card mb-3" style="max-width: 100%;">--%>
<%--                            <div class="row no-gutters">--%>
<%--                                <div class="col-md-4 pt-3 pl-5">--%>
<%--                                    <a href="/user_page?user_id=<%=f.getRequest_sender_id()%>"><img src="<%=f.getRequest_sender_id().getPicture_url()%>" class="rounded-circle" alt="..." width="100" height="100"></a>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-8">--%>
<%--                                    <div class="card-body">--%>
<%--                                        <h5 class="card-title"><a href="/user_page?user_id=<%=f.getRequest_sender_id()%>"><%=f.getRequest_sender_id().getFull_name()%></a></h5>--%>
<%--                                        <p class="card-text"><small class="text-muted"><%=f.getRequest_sender_id().getAge()%> old</small></p>--%>
<%--                                        <button><i class="fab fa-telegram-plane"></i> Send Message</button>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </form>--%>