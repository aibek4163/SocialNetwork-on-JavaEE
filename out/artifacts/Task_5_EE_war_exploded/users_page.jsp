<%@ page import="aralasu.kz.db.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="aralasu.kz.db.Friend" %>
<%@ page import="aralasu.kz.db.Friend_Requests" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 08.10.2020
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>User Page</title>
</head>
    <body>
    <%@include file="include/navbar.jsp"%>
        <div class="container">
            <div class="row">
                <%
                    User u = (User)request.getAttribute("user");
                    ArrayList<Friend> friends = (ArrayList<Friend>)request.getAttribute("friends");
                    ArrayList<Friend_Requests> friend_requests = (ArrayList<Friend_Requests>)request.getAttribute("friend_requests");
                    if(u!=null){
                %>
                <%
                    String message_sent = request.getParameter("message_sent");
                    if(message_sent!=null){
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    message_sent!
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>
                <%
                    String error = request.getParameter("error");
                    if(error!=null){
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    error!
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>
                <div class="col-3">
                    <img src="<%=u.getPicture_url()%>" height="200" class="w-100">
                    <div class="card mt-5" style="width: 100%;">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><%=u.getFull_name()%>, <%=u.getAge() + " years"%></li>
                            <%
                                boolean check = false,request_check = false;
                                if(friends!=null){
                                    for(Friend f:friends){
                                        if(user.getId().equals(f.getUser_id().getId()) && u.getId().equals(f.getFriend_id().getId())){
                            %>
<%--                            <li class="list-group-item"><a href="/add_friend?remove_friend=<%=f.getId()%>"><i class="fas fa-minus-circle"></i> Remove from Friend</a></li>--%>
                            <li class="list-group-item"><a href="/add_friend?remove_friend=<%=u.getId()%>"><i class="fas fa-minus-circle"></i> Remove from Friend</a></li>
                            <%
                                            check = true;
                                        }
                                    }if(!check){
                                        boolean check_request = false;
                                        for(Friend_Requests fr:friend_requests){
                                            if(user.getId().equals(fr.getRequest_sender_id().getId()) && u.getId().equals(fr.getUser_id().getId())){
                                                check_request = true;
                            %>
                            <li class="list-group-item"><a href="/add_friend?request_sent=<%=fr.getId()%>"><i class="fas fa-check"></i> Request Sent </a></li>
                            <%
                                    }else if(user.getId().equals(fr.getRequest_sender_id().getId())){
                                                check_request=true;
                            %>
                            <li class="list-group-item"><a href="/add_friend?confirm=<%=u.getId()%>" ><i class="fas fa-plus-circle"></i> Confirm </a></li>
                            <li class="list-group-item"><a href="/add_friend?reject=<%=u.getId()%>"><i class="fas fa-minus-circle"></i> Reject </a></li>
                            <%
                                    }
                            %>

                            <%
                                }if(!check_request ){
                            %>
                            <li class="list-group-item"><a href="/add_friend?request_sender_id=<%=u.getId()%>"><i class="fas fa-plus-circle"></i> Add Friend</a></li>
                            <%
                                        }
                                    }
                                }
                            %>
<%--                            <li class="list-group-item"><a href="#"><i class="fab fa-telegram-plane"></i> Send Message</a></li>--%>
                            <li class="list-group-item">
                                <!-- Button trigger modal -->
                                <button type="button" class="btn" data-toggle="modal" data-target="#exampleModal" style="padding: 0;"><a href="#">
                                    <i class="fab fa-telegram-plane"></i>
                                     Send Message
                                </a>
                                </button>
                            </li>
                            <li class="list-group-item"><a href="/logout" style="color: #862F3B"><i class="fas fa-sign-out-alt"></i> Log out</a></li>
                        </ul>
                    </div>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <form action="/add_message" method="post">
                            <input type="hidden" name="receiver_id" value="<%=u.getId()%>">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Send Message to <%=u.getFull_name()%></h5>
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

                <div class="col-6">
                    <%
                        ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("post_user");
                        if(posts!=null){
                            for(Post p:posts){
                    %>
                    <form action="/details_post" method="get">
                        <input type="hidden" name="id" value="<%=p.getId()%>">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><%=p.getTitle()%></h5>
                                <p class="card-text"><%=p.getShort_content()%></p>
                                <a href="/details_post?id=<%=p.getId()%>" style="text-decoration: none;"><button type="button" class="btn btn-sm" style="border-color: #180B7A;color:#180B7A;">More--></button></a>
                            </div>
                            <div class="card-footer text-muted d-flex">
                                Posted on <%=p.getPost_date()%> by <p style="color: #180B7A">  <%=p.getUser_id().getFull_name()%></p>
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
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
