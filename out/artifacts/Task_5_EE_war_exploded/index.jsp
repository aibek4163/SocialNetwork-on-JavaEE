<%@ page import="aralasu.kz.db.Post" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 04.10.2020
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Feed</title>
</head>
    <body>
        <%@include file="include/navbar.jsp"%>
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <img src="<%=user.getPicture_url()%>" height="200" class="w-100">
                    <div class="card mt-5" style="width: 100%;">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><%=user.getFull_name()%>, <%=user.getAge() + " years"%> </li>
                            <li class="list-group-item"><a href="/profile"><i class="fas fa-id-card"></i> My Profile</a></li>
                            <li class="list-group-item"><a href="#"><i class="fas fa-cogs"></i> Settings</a></li>
                            <li class="list-group-item"><a href="/logout" style="color: #862F3B"><i class="fas fa-sign-out-alt"></i> Log out</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-6">
                    <%
                        ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
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
            </div>
        </div>
    </body>
</html>
